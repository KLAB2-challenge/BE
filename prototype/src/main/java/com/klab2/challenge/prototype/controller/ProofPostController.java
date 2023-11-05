package com.klab2.challenge.prototype.controller;

import com.klab2.challenge.prototype.dto.request.GetProofPostsRequest;
import com.klab2.challenge.prototype.dto.request.SetProofPostRequest;
import com.klab2.challenge.prototype.dto.response.GetProofPostResponse;
import com.klab2.challenge.prototype.dto.response.GetProofPostsResponse;
import com.klab2.challenge.prototype.dto.response.SetProofPostResponse;
import com.klab2.challenge.prototype.service.ProofPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/proofPost")
public class ProofPostController {

    private final ProofPostService proofPostService;

    @PostMapping("/setProofPost")
    public ResponseEntity<SetProofPostResponse> setProofPostRequestResponseEntity(@RequestBody @Valid SetProofPostRequest request){
        SetProofPostResponse response = proofPostService.setProofPost(request.getChallengeId(), request.getMemberName(), request.getContents());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getProofPost")
    public ResponseEntity<GetProofPostResponse> getProofPost(@RequestParam("proofPostId") Long proofPostId){
        GetProofPostResponse response = proofPostService.getProofPost(proofPostId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAllProofPosts")
    public ResponseEntity<GetProofPostsResponse> getProofPosts(@RequestParam("challengeId") Long challengeId){
        GetProofPostsResponse response = proofPostService.getAllProofPosts(challengeId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getProofPosts")
    public ResponseEntity<GetProofPostsResponse> getProofPost(@RequestBody @Valid GetProofPostsRequest request){
        GetProofPostsResponse response = proofPostService.getProofPosts(request.getChallengeId(), request.getPage(), request.getSize());
        return ResponseEntity.ok(response);
    }
}
