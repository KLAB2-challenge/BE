package com.klab2.challenge.prototype.controller;

import com.klab2.challenge.prototype.dto.request.GetProofPostsRequest;
import com.klab2.challenge.prototype.dto.response.GetProofPostResponse;
import com.klab2.challenge.prototype.dto.response.GetProofPostsResponse;
import com.klab2.challenge.prototype.service.ProofPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/proofPost")
public class ProofPostController {

    private final ProofPostService proofPostService;

    @PostMapping("/getProofPosts")
    public ResponseEntity<GetProofPostsResponse> getProofPost(@RequestBody @Valid GetProofPostsRequest request){
        GetProofPostsResponse response = proofPostService.getProofPosts(request.getChallengeId(),request.getNum());
        return ResponseEntity.ok(response);
    }

}
