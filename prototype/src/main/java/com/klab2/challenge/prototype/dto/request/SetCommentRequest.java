package com.klab2.challenge.prototype.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SetCommentRequest {

    @NotNull(message = "memberName을 전달해주세요.")
    String memberName;

    @NotNull(message = "content를 전달해주세요.")
    String content;

    @NotNull(message = "proofPostId를 전달해주세요.")
    Long proofPostId;
}
