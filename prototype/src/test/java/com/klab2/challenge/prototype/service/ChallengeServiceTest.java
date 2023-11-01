package com.klab2.challenge.prototype.service;

import com.klab2.challenge.prototype.domain.*;
import com.klab2.challenge.prototype.repository.ChallengeRepository;
import com.klab2.challenge.prototype.repository.MemberChallengeRepository;
import com.klab2.challenge.prototype.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
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
    @Autowired
    private MemberChallengeRepository memberChallengeRepository;

    private String memberName;

    @BeforeEach
    public void beforeEach() {
        Member member = new Member("user");
        memberName = memberRepository.save(member).getName();
    }

    @AfterEach
    public void afterEach() {
        memberChallengeRepository.deleteAll();
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

    @Test
    @DisplayName("챌린지를 생성하고, 해당 챌린지의 정보를 가져올 수 있다.")
    public void getChallengeService() {
        // given
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        ChallengeContents contents = new ChallengeContents("title", "image", "content");
        ChallengeInfos infos = new ChallengeInfos("11/1", "12/1", "1주 1회", 1, true);
        Challenge challenge = new Challenge(member1, contents, infos);
        challengeRepository.save(challenge);

        memberChallengeRepository.save(new MemberChallenge(member2, challenge));

        // when
        Integer memberNum = challengeService.getChallenge(memberName, challenge.getChallengeId()).getMemberNum();

        // then
        Assertions.assertThat(memberNum).isEqualTo(2);
    }
}