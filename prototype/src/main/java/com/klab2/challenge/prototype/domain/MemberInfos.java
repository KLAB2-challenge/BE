package com.klab2.challenge.prototype.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberInfos {
    private int totalCoins;
    private int holdingCoins;
    private Long currentBorder;
    private String imageUrl = "https://klab2-challenge-app.s3.ap-northeast-2.amazonaws.com/defaultImage.png";
}