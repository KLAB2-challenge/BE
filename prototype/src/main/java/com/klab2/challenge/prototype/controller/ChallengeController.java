package com.klab2.challenge.prototype.controller;


import com.klab2.challenge.prototype.dto.request.SetChallengeRequest;
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
}
