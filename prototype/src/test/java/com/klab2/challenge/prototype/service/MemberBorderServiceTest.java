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
public class MemberBorderServiceTest {

    @Autowired
    private MemberBorderService memberBorderService;
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
    @DisplayName("Border 구매를 수행한다.")
    public void buyBorderService() {
        // given
        Member member = new Member("member");
        Border border1 = new Border();
        Border border2 = new Border();

        memberRepository.save(member);
        borderRepository.save(border1);
        borderRepository.save(border2);

        // when
        memberBorderService.buyBorder(member.getName(), border1.getId());
        memberBorderService.buyBorder(member.getName(), border2.getId());
        List<MemberBorder> memberBorders = memberBorderRepository.findAll();

        // then
        Assertions.assertThat(memberBorders).hasSize(2);
    }
}
