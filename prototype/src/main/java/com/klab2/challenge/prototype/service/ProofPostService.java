package com.klab2.challenge.prototype.service;

import com.klab2.challenge.prototype.domain.Challenge;
import com.klab2.challenge.prototype.domain.Member;
import com.klab2.challenge.prototype.domain.ProofPost;
import com.klab2.challenge.prototype.domain.ProofPostContents;
import com.klab2.challenge.prototype.dto.response.GetProofPostResponse;
import com.klab2.challenge.prototype.dto.response.GetProofPostsResponse;
import com.klab2.challenge.prototype.dto.response.SetProofPostResponse;
import com.klab2.challenge.prototype.repository.ChallengeRepository;
import com.klab2.challenge.prototype.repository.CommentRepository;
import com.klab2.challenge.prototype.repository.MemberRepository;
import com.klab2.challenge.prototype.repository.ProofPostRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProofPostService {

    private final ProofPostRepository proofPostRepository;
    private final ChallengeRepository challengeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public SetProofPostResponse setProofPost(Long challengeId, String memberName, ProofPostContents contents){
        Member member = memberRepository.findByName(memberName).get();
        Challenge challenge = challengeRepository.findById(challengeId).get();
        ProofPost proofPost = new ProofPost(contents, challenge, member);

        long proofPostId = proofPostRepository.save(proofPost).getProofPostId();
        return new SetProofPostResponse(proofPostId);
    }

    @Transactional(readOnly=true)
    public GetProofPostResponse getProofPost(Long proofPostId) {
        ProofPost proofPost = proofPostRepository.findProofPostByProofPostId(proofPostId).get();

        return new GetProofPostResponse(proofPost.getProofPostId(), proofPost.getMember().getName(), proofPost.getContents());
    }

    @Transactional(readOnly=true)
    public GetProofPostsResponse getProofPosts(long challengeId, int page, int size) {
        Challenge challenge = challengeRepository.findById(challengeId).get();

        PageRequest pageRequest = PageRequest.of(page, size);

        List<GetProofPostResponse> getProofPostResponses =
                proofPostRepository.findSomeProofPostsByChallengeId(challenge.getChallengeId(), pageRequest)
                        .stream()
                        .map(proofPost -> {

                            return new GetProofPostResponse(
                                    proofPost.getProofPostId(),
                                    proofPost.getMember().getName(),
                                    new ProofPostContents(
                                            proofPost.getContents().getTitle(),
                                            proofPost.getContents().getContent(),
                                            proofPost.getContents().getImage()
                                    )
                            );
                        }).toList();

        return new GetProofPostsResponse(getProofPostResponses);
    }

    @Transactional(readOnly=true)
    public GetProofPostsResponse getAllProofPosts(long challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId).get();

        List<GetProofPostResponse> getProofPostResponses =
                proofPostRepository.findAllProofPostsByChallengeId(challengeId)
                        .stream()
                        .map(proofPost -> {
                            return new GetProofPostResponse(
                                    proofPost.getProofPostId(),
                                    proofPost.getMember().getName(),
                                    new ProofPostContents(
                                            proofPost.getContents().getTitle(),
                                            proofPost.getContents().getContent(),
                                            proofPost.getContents().getImage()
                                    )
                            );
                        })
                        .toList();

        return new GetProofPostsResponse(getProofPostResponses);
    }
}