package com.klab2.challenge.prototype.dto.request;

import com.klab2.challenge.prototype.domain.ChallengeContents;
import com.klab2.challenge.prototype.domain.ChallengeInfos;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SetChallengeRequest {

    @NotNull(message = "memberName을 전달해주세요.")
    private String memberName;

    @NotNull(message = "contents를 전달해주세요.")
    private ChallengeContents contents;

    @NotNull(message = "infos를 전달해주세요.")
    private ChallengeInfos infos;
}
