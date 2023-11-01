package com.klab2.challenge.prototype.service;

import com.klab2.challenge.prototype.domain.Challenge;
import com.klab2.challenge.prototype.domain.ChallengeContents;
import com.klab2.challenge.prototype.domain.ChallengeInfos;
import com.klab2.challenge.prototype.domain.Member;
import com.klab2.challenge.prototype.repository.ChallengeRepository;
import com.klab2.challenge.prototype.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ChallengeServiceTest {

    @Autowired
    private ChallengeService challengeService;
    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private MemberRepository memberRepository;

    private String memberName;

    @BeforeEach
    public void beforeAll() {
        Member member = new Member("user");
        memberName = memberRepository.save(member).getName();
    }

    @AfterEach
    public void afterEach() {
        challengeRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("챌린지를 정상적으로 성공한다.")
    public void setChallengeService() {
        // given
        ChallengeContents contents = new ChallengeContents("title", "image", "content");
        ChallengeInfos infos = new ChallengeInfos("11/1", "12/1", "1주 1회", 1, true);

        // when
        Long challengeId = challengeService.setChallenge(memberName, contents, infos).getChallengeId();

        // then
        List<Challenge> found = challengeRepository.findAll();
        Assertions.assertThat(found).hasSize(1);
        Assertions.assertThat(found.get(0).getChallengeId()).isEqualTo(challengeId);
    }
}