package com.klab2.challenge.prototype.service;

import com.klab2.challenge.prototype.domain.Challenge;
import com.klab2.challenge.prototype.domain.ProofPost;
import com.klab2.challenge.prototype.dto.response.GetProofPostsResponse;
import com.klab2.challenge.prototype.repository.ChallengeRepository;
import com.klab2.challenge.prototype.repository.CommentRepository;
import com.klab2.challenge.prototype.repository.ProofPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProofPostService {

    private final ProofPostRepository proofPostRepository;
    private final CommentRepository commentRepository;
    private final ChallengeRepository challengeRepository;



    public GetProofPostsResponse getProofPosts(long challengeId, long num) {
        Challenge challenge = challengeRepository.findById(challengeId).get();
        Optional<ProofPost> proofPosts = proofPostRepository.findByChallenge(challenge.getChallengeId());
        return new GetProofPostsResponse(proofPosts.stream().toList());

    }

}