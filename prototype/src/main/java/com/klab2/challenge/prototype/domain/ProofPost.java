package com.klab2.challenge.prototype.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProofPost {
    private long ProofPostId;
    private long ChallengeId;
    private long userID;
    private String title;
    private String content;
    private String image;
}
