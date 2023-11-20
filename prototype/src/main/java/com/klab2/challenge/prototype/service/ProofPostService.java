package com.klab2.challenge.prototype.service;

import com.klab2.challenge.prototype.domain.*;
import com.klab2.challenge.prototype.dto.response.GetProofPostResponse;
import com.klab2.challenge.prototype.dto.response.GetProofPostsResponse;
import com.klab2.challenge.prototype.dto.response.SetProofPostResponse;
import com.klab2.challenge.prototype.repository.ChallengeRepository;
import com.klab2.challenge.prototype.repository.CommentRepository;
import com.klab2.challenge.prototype.repository.MemberRepository;
import com.klab2.challenge.prototype.repository.ProofPostRepository;
import com.klab2.challenge.prototype.s3.AwsS3FileSupporter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProofPostService {

    private final ProofPostRepository proofPostRepository;
    private final ChallengeRepository challengeRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final AwsS3FileSupporter awsS3FileSupporter;

    @Value("${s3.defaultProofPostImage}")
    private String defaultImage;

    @Transactional
    public SetProofPostResponse setProofPost(Long challengeId, String memberName, ProofPostContents contents, MultipartFile image) throws IOException {
        Member member = memberRepository.findByName(memberName).get();
        Challenge challenge = challengeRepository.findById(challengeId).get();

        if(Objects.isNull(image)) {
            contents.setImage(defaultImage);
        }
        else {
            String imageUrl = awsS3FileSupporter.uploadImage(image, "proofPostImages/");
            contents.setImage(imageUrl);
        }

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String formattedDate = simpleDateFormat.format(date);
        ProofPostInfos infos = new ProofPostInfos(formattedDate);

        ProofPost proofPost = new ProofPost(challenge, member, contents, infos);

        long proofPostId = proofPostRepository.save(proofPost).getProofPostId();
        return new SetProofPostResponse(proofPostId);
    }

    @Transactional(readOnly=true)
    public GetProofPostResponse getProofPost(Long proofPostId) {
        ProofPost proofPost = proofPostRepository.findProofPostByProofPostId(proofPostId).get();
        int commentNum = commentRepository.findCommentsNumByProofPost(proofPostId);

        return new GetProofPostResponse(
                proofPost.getProofPostId(),
                proofPost.getMember().getName(),
                proofPost.getContents(),
                proofPost.getInfos(),
                commentNum);
    }

    @Transactional(readOnly=true)
    public GetProofPostsResponse getProofPosts(long challengeId, int page, int size) {
        Challenge challenge = challengeRepository.findById(challengeId).get();

        PageRequest pageRequest = PageRequest.of(page, size);

        List<GetProofPostResponse> getProofPostResponses =
                proofPostRepository.findSomeProofPostsByChallengeId(challenge.getChallengeId(), pageRequest)
                        .stream()
                        .map(proofPost -> {
                            int commentNum = commentRepository.findCommentsNumByProofPost(proofPost.getProofPostId());

                            return new GetProofPostResponse(
                                    proofPost.getProofPostId(),
                                    proofPost.getMember().getName(),
                                    new ProofPostContents(
                                            proofPost.getContents().getTitle(),
                                            proofPost.getContents().getContent(),
                                            proofPost.getContents().getImage()
                                    ),
                                    proofPost.getInfos(),
                                    commentNum
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
                            int commentNum = commentRepository.findCommentsNumByProofPost(proofPost.getProofPostId());

                            return new GetProofPostResponse(
                                    proofPost.getProofPostId(),
                                    proofPost.getMember().getName(),
                                    new ProofPostContents(
                                            proofPost.getContents().getTitle(),
                                            proofPost.getContents().getContent(),
                                            proofPost.getContents().getImage()
                                    ),
                                    proofPost.getInfos(),
                                    commentNum
                            );
                        }).toList();

        return new GetProofPostsResponse(getProofPostResponses);
    }
}