package com.klab2.challenge.prototype.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetMemberAllChallengesResponse {
    private List<GetChallengeResponse> challenges;
}
