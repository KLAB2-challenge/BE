package com.klab2.challenge.prototype.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SetMemberCoinsRequest {

    @NotNull(message = "memberName을 전달해주세요.")
    private String memberName;

    @NotNull(message = "coins를 전달해주세요.")
    private int coins;
}