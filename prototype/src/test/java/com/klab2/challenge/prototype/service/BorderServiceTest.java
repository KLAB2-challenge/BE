package com.klab2.challenge.prototype.service;

import com.klab2.challenge.prototype.domain.Border;
import com.klab2.challenge.prototype.domain.Member;
import com.klab2.challenge.prototype.domain.MemberBorder;
import com.klab2.challenge.prototype.repository.BorderRepository;
import com.klab2.challenge.prototype.repository.MemberBorderRepository;
import com.klab2.challenge.prototype.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BorderServiceTest {

    @Autowired
    private BorderService borderService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BorderRepository borderRepository;
    @Autowired
    private MemberBorderRepository memberBorderRepository;

    @AfterEach
    public void afterEach() {
        memberBorderRepository.deleteAll();
        borderRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("유저의 모든 border를 가져온다.")
    public void setMemberCoinsService() {
        // given
        Member member = new Member("member");

        Border border1 = new Border();
        Border border2 = new Border();

        MemberBorder memberBorder1 = new MemberBorder(member, border1);
        MemberBorder memberBorder2 = new MemberBorder(member, border2);

        memberRepository.save(member);
        borderRepository.save(border1);
        borderRepository.save(border2);
        memberBorderRepository.save(memberBorder1);
        memberBorderRepository.save(memberBorder2);

        // when
        List<Long> borderIds = borderService.getMemberAllBorders(member.getName()).getBorderIds();

        // then
        Assertions.assertThat(borderIds).hasSize(2);
    }
}
