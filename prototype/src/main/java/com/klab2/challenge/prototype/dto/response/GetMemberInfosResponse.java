package com.klab2.challenge.prototype.dto.response;

import com.klab2.challenge.prototype.domain.Member;
import com.klab2.challenge.prototype.domain.MemberInfos;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetMemberInfosResponse {
    private String memberName;
    private MemberInfos infos;
}