package com.klab2.challenge.prototype.controller;


import com.klab2.challenge.prototype.dto.request.GetChallengeRequest;
import com.klab2.challenge.prototype.dto.request.GetPopularChallengesRequest;
import com.klab2.challenge.prototype.dto.request.SetChallengeRequest;
import com.klab2.challenge.prototype.dto.response.GetChallengeResponse;
import com.klab2.challenge.prototype.dto.response.GetPopularChallengesResponse;
import com.klab2.challenge.prototype.dto.response.SetChallengeResponse;
import com.klab2.challenge.prototype.service.ChallengeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/challenge")
public class ChallengeController {

    private final ChallengeService challengeService;

    @PostMapping("/setChallenge")
    public ResponseEntity<SetChallengeResponse> setChallenge(@RequestBody @Valid SetChallengeRequest request) {
        SetChallengeResponse response = challengeService.setChallenge(request.getMemberName(), request.getContents(), request.getInfos());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getChallenge")
    public ResponseEntity<GetChallengeResponse> getChallenge(@RequestBody @Valid GetChallengeRequest request) {
        GetChallengeResponse response = challengeService.getChallenge(request.getMemberName(), request.getChallengeId());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getPopularChallenges")
    public ResponseEntity<GetPopularChallengesResponse> getChallenge(@RequestBody @Valid GetPopularChallengesRequest request) {
        GetPopularChallengesResponse response = challengeService.getPopularChallenges(request.getMemberName(), request.getPage(), request.getSize());
        return ResponseEntity.ok(response);
    }
}
