package com.klab2.challenge.prototype.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SetProofPostRequest {
    private int challengeId;
    private String userId;
    private String title;
    private String content;
    private String image;
}
