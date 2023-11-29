package com.klab2.challenge.prototype.controller;

import com.klab2.challenge.prototype.dto.request.GetProofPostsRequest;
import com.klab2.challenge.prototype.dto.request.SetProofPostRequest;
import com.klab2.challenge.prototype.dto.response.GetProofPostResponse;
import com.klab2.challenge.prototype.dto.response.GetProofPostsResponse;
import com.klab2.challenge.prototype.dto.response.SetProofPostResponse;
import com.klab2.challenge.prototype.service.ProofPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/proofPost")
public class ProofPostController {

    private final ProofPostService proofPostService;

    @PostMapping("/setProofPost")
    public ResponseEntity<SetProofPostResponse> setProofPostRequestResponseEntity(@RequestPart(value = "image", required = false) MultipartFile image,
                                                                                  @RequestPart(value = "proofPost") @Valid SetProofPostRequest request) throws IOException {
        log.info("user: {}, request: /proofPost/setProofPost", request.getMemberName());
        SetProofPostResponse response = proofPostService.setProofPost(request.getChallengeId(), request.getMemberName(), request.getContents(), image);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getProofPost")
    public ResponseEntity<GetProofPostResponse> getProofPost(@RequestParam("proofPostId") Long proofPostId){
        log.info("user: Unknown, request: /proofPost/getProofPost");
        GetProofPostResponse response = proofPostService.getProofPost(proofPostId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAllProofPosts")
    public ResponseEntity<GetProofPostsResponse> getProofPosts(@RequestParam("challengeId") Long challengeId){
        log.info("user: Unknown, request: /proofPost/getAllProofPosts");
        GetProofPostsResponse response = proofPostService.getAllProofPosts(challengeId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getProofPosts")
    public ResponseEntity<GetProofPostsResponse> getProofPost(@RequestBody @Valid GetProofPostsRequest request){
        log.info("user: Unkonwn, request: /proofPost/getProofPosts");
        GetProofPostsResponse response = proofPostService.getProofPosts(request.getChallengeId(), request.getPage(), request.getSize());
        return ResponseEntity.ok(response);
    }
}
