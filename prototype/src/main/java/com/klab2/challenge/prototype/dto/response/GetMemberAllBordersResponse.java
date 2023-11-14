package com.klab2.challenge.prototype.dto.response;

import com.klab2.challenge.prototype.domain.Border;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetMemberAllBordersResponse {
    private List<Border> borders;
}
