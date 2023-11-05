package com.klab2.challenge.prototype.dto.request;

import com.klab2.challenge.prototype.domain.ProofPostContents;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SetProofPostRequest {

    @NotNull(message = "challengeId를 전달해주세요.")
    private Long challengeId;

    @NotNull(message = "memberName을 전달해주세요")
    private String memberName;

    @NotNull(message = "contents를 전달해주세요")
    private ProofPostContents contents;
}
