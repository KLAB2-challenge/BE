package com.klab2.challenge.prototype.controller;

import com.klab2.challenge.prototype.dto.request.JoinChallengeRequest;
import com.klab2.challenge.prototype.dto.response.JoinChallengeResponse;
import com.klab2.challenge.prototype.service.MemberChallengeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memberChallenge")
public class MemberChallengeController {

    private final MemberChallengeService memberChallengeService;

    @PostMapping("/joinChallenge")
    public ResponseEntity<JoinChallengeResponse> setChallenge(@RequestBody @Valid JoinChallengeRequest request) {
        JoinChallengeResponse response = memberChallengeService.joinChallenge(request.getMemberName(), request.getChallengeId());
        return ResponseEntity.ok(response);
    }
}
