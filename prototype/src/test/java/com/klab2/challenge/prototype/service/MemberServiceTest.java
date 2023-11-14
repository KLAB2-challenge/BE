package com.klab2.challenge.prototype.service;

import com.klab2.challenge.prototype.domain.*;
import com.klab2.challenge.prototype.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    public void afterEach() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("유저의 코인을 갱신한다.")
    public void setMemberCoinsService() {
        // given
        Member member = new Member("member");
        memberRepository.save(member);

        // when
        memberService.setMemberCoins(member.getName(), 20);
        int totalCoins = memberRepository.findByName("member").get().getInfos().getTotalCoins();
        int holdingCoins = memberRepository.findByName("member").get().getInfos().getHoldingCoins();

        // then
        Assertions.assertThat(totalCoins).isEqualTo(20);
        Assertions.assertThat(holdingCoins).isEqualTo(20);
    }

    @Test
    @DisplayName("유저의 테두리를 변경한다.")
    public void changeCurrentBorderService() {
        // given
        Member member = new Member("member");
        memberRepository.save(member);

        // when
        memberService.changeCurrentBorder(member.getName(), 2L);
        Long currentBorder = memberRepository.findById(member.getMemberId()).get().getInfos().getCurrentBorder();

        // then
        Assertions.assertThat(currentBorder).isEqualTo(2L);
    }

    @Test
    @DisplayName("유저의 정보를 가져온다.")
    public void getMemberInfosService() {
        // given
        Member member = new Member("member");
        memberRepository.save(member);

        // when
        String memberName = memberService.getMemberInfos(member.getName()).getMemberName();

        // then
        Assertions.assertThat(memberName).isEqualTo(member.getName());
    }
}
