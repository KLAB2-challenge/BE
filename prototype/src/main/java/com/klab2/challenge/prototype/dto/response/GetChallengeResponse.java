package com.klab2.challenge.prototype.dto.response;

import com.klab2.challenge.prototype.domain.ChallengeContents;
import com.klab2.challenge.prototype.domain.ChallengeInfos;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetChallengeResponse {
    private Long challengeId;
    private ChallengeContents contents;
    private ChallengeInfos infos;
    private int memberNum;
    private boolean isJoin;
}
