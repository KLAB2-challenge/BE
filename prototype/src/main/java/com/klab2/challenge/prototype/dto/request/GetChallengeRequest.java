package com.klab2.challenge.prototype.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetChallengeRequest {
    private String memberName;
    private Long challengeId;
}
