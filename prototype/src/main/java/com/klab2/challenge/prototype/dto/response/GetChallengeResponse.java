package com.klab2.challenge.prototype.dto.response;

import com.klab2.challenge.prototype.domain.ChallengeContents;
import com.klab2.challenge.prototype.domain.ChallengeInfos;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetChallengeResponse {
    private ChallengeContents contents;
    private ChallengeInfos infos;
    private Integer memberNum;
}