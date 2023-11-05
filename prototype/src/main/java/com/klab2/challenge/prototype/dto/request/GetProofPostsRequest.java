package com.klab2.challenge.prototype.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetProofPostsRequest {

    @NotNull(message = "challengeId를 전달해주세요.")
    private Long challengeId;

    @NotNull(message = "page를 전달해주세요.")
    private int page;

    @NotNull(message = "size를 전달해주세요.")
    private int size;
}
