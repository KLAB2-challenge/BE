package com.klab2.challenge.prototype.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeInfos {
    private String userName;
    private String startDate;
    private String endDate;
    private String frequency;
    private Integer category;
    private Boolean type;
}
