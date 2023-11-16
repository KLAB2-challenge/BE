package com.klab2.challenge.prototype.dto.response;


import com.klab2.challenge.prototype.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetRankResponse {
    private int my_rank;
    private List<Member> ranker;
}
