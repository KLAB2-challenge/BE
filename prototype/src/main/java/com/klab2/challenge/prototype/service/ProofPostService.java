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
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProofPostService {

    private final ProofPostRepository proofPostRepository;
    private final CommentRepository commentRepository;
    private final ChallengeRepository challengeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public SetProofPostResponse setProofPost(long challengeId, String memberName, ProofPostContents contents){
        Member member = memberRepository.findByName(memberName).get();
        Challenge challenge = challengeRepository.findById(challengeId).get();
        ProofPost proofPost = new ProofPost(contents, challenge, member);

        long proofPostId = proofPostRepository.save(proofPost).getProofPostId();
        return new SetProofPostResponse(proofPostId);
    }

    @Transactional(readOnly=true)
    public GetProofPostsResponse getProofPosts(long challengeId, long num) {
        Challenge challenge = challengeRepository.findById(challengeId).get();
        List<ProofPost> proofPosts = proofPostRepository.findByChallengeId(challenge.getChallengeId()).subList(0,(int)num);
        List<GetProofPostResponse> proofPostResponses = proofPosts.stream().map(
                proofPost -> {
                    return new GetProofPostResponse(
                            proofPost.getProofPostId(),
                            proofPost.getMember().getName(),
                            proofPost.getContents().getTitle(),
                            proofPost.getContents().getContent(),
                            proofPost.getContents().getImage()
                    );
                }
        ).toList();
        return new GetProofPostsResponse(proofPostResponses);
    }
}