package com.klab2.challenge.prototype.controller;


import com.klab2.challenge.prototype.dto.request.*;
import com.klab2.challenge.prototype.dto.response.*;
import com.klab2.challenge.prototype.service.ChallengeService;
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
@RequestMapping("/challenge")
public class ChallengeController {

    private final ChallengeService challengeService;

    @PostMapping("/setChallenge")
    public ResponseEntity<SetChallengeResponse> setChallenge(@RequestPart(value = "image", required = false) MultipartFile image,
                                                             @RequestPart(value = "challenge") @Valid SetChallengeRequest request) throws IOException {
        log.info("user: {}, request: /challenge/setChallenge", request.getMemberName());
        SetChallengeResponse response = challengeService.setChallenge(request.getMemberName(), request.getContents(), request.getInfos(), image);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getChallenge")
    public ResponseEntity<GetChallengeResponse> getChallenge(@RequestBody @Valid GetChallengeRequest request) {
        log.info("user: {}, request: /challenge/getChallenge", request.getMemberName());
        GetChallengeResponse response = challengeService.getChallenge(request.getMemberName(), request.getChallengeId());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getPopularChallenges")
    public ResponseEntity<GetPopularChallengesResponse> getChallenge(@RequestBody @Valid GetPopularChallengesRequest request) {
        log.info("user: {}, request: /challenge/getPopularChallenges", request.getMemberName());
        GetPopularChallengesResponse response = challengeService.getPopularChallenges(request.getMemberName(), request.getPage(), request.getSize());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getOfficialOrUserChallenges")
    public ResponseEntity<GetOfficialOrUserChallengesResponse> getChallenge(@RequestBody @Valid GetOfficialOrUserChallengesRequest request) {
        log.info("user: {}, request: /challenge/getOfficialOrUserChallenges", request.getMemberName());
        GetOfficialOrUserChallengesResponse response =
                challengeService.getOfficialOrUserChallenges(request.getMemberName(), request.getPage(), request.getSize(), request.isType());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getRelatedChallenges")
    public ResponseEntity<GetRelatedChallengesResponse> getChallenge(@RequestBody @Valid GetRelatedChallengesRequest request) {
        log.info("user: {}, request: /challenge/getRelatedChallenges", request.getMemberName());
        GetRelatedChallengesResponse response = challengeService.getRelatedChallenges(request.getMemberName(), request.getPage(), request.getSize(), request.getCategory());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getMemberAllChallenges")
    public ResponseEntity<GetMemberAllChallengesResponse> getChallenge(@RequestBody @Valid GetMemberAllChallengesRequest request) {
        log.info("user: {}, request: /challenge/getMemberAllChallenges", request.getMemberName());
        GetMemberAllChallengesResponse response = challengeService.getMemberAllChallenges(request.getMemberName(), request.getPage(), request.getSize());
        return ResponseEntity.ok(response);
    }
}
