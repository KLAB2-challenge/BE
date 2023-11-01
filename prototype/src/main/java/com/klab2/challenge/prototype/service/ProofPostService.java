package com.klab2.challenge.prototype.service;

import com.klab2.challenge.prototype.dto.response.SetProofPostResponse;
import com.klab2.challenge.prototype.repository.CommentRepository;
import com.klab2.challenge.prototype.repository.ProofPostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProofPostService {

    private final ProofPostRepository proofPostRepository;
    private final CommentRepository commentRepository;

//    @Transactional
//    public SetProofPostResponse setProofPost(String userName, Long id, ) {
//
//    }
}