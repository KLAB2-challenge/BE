package com.klab2.challenge.prototype.service;

import com.klab2.challenge.prototype.domain.Border;
import com.klab2.challenge.prototype.domain.Member;
import com.klab2.challenge.prototype.dto.response.GetMemberAllBordersResponse;
import com.klab2.challenge.prototype.repository.MemberBorderRepository;
import com.klab2.challenge.prototype.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BorderService {

    private final MemberRepository memberRepository;
    private final MemberBorderRepository memberBorderRepository;

    @Transactional(readOnly = true)
    public GetMemberAllBordersResponse getMemberAllBorders(String memberName) {
        Member member = memberRepository.findByName(memberName).get();
        List<Border> borders = memberBorderRepository.getMemberAllBorders(member.getMemberId());

        return new GetMemberAllBordersResponse(borders);
    }
}
