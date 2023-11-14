package com.klab2.challenge.prototype.service;

import com.klab2.challenge.prototype.domain.Border;
import com.klab2.challenge.prototype.domain.Member;
import com.klab2.challenge.prototype.domain.MemberBorder;
import com.klab2.challenge.prototype.dto.response.BuyBorderResponse;
import com.klab2.challenge.prototype.repository.BorderRepository;
import com.klab2.challenge.prototype.repository.MemberBorderRepository;
import com.klab2.challenge.prototype.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberBorderService {

    private final MemberRepository memberRepository;
    private final BorderRepository borderRepository;
    private final MemberBorderRepository memberBorderRepository;

    @Transactional
    public BuyBorderResponse buyBorder(String memberName, Long borderId) {
        Member member = memberRepository.findByName(memberName).get();
        Border border = borderRepository.findById(borderId).get();

        if(memberBorderRepository.findMemberBorderByMemberAndBorder(member, border).isEmpty())
            memberBorderRepository.save(new MemberBorder(member, border));

        return new BuyBorderResponse(true);
    }
}
