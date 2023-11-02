package com.klab2.challenge.prototype.service;

import com.klab2.challenge.prototype.domain.*;
import com.klab2.challenge.prototype.dto.response.GetPopularChallengesResponse;
import com.klab2.challenge.prototype.repository.ChallengeRepository;
import com.klab2.challenge.prototype.repository.MemberChallengeRepository;
import com.klab2.challenge.prototype.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
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

    private Member member;

    @BeforeEach
    public void beforeEach() {
        member = new Member("user");
        memberRepository.save(member);
    }

    @AfterEach
    public void afterEach() {
        memberChallengeRepository.deleteAll();
        challengeRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("챌린지를 정상적으로 생성한다.")
    public void setChallengeService() {
        // given
        ChallengeContents contents = new ChallengeContents("title", "image", "content");
        ChallengeInfos infos = new ChallengeInfos("11/1", "12/1", "1주 1회", 1, true);

        // when
        Long challengeId = challengeService.setChallenge(member.getName(), contents, infos).getChallengeId();

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

        memberChallengeRepository.save(new MemberChallenge(member1, challenge));
        memberChallengeRepository.save(new MemberChallenge(member2, challenge));

        // when
        Integer memberNum = challengeService.getChallenge(member.getName(), challenge.getChallengeId()).getMemberNum();

        // then
        Assertions.assertThat(memberNum).isEqualTo(2);
    }

    @Test
    @DisplayName("인기 챌린지를 가져온다.")
    public void getPopularChallengesService() {
        // given
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Challenge> challenges = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            ChallengeContents contents = new ChallengeContents("title"+i, "image", "content");
            ChallengeInfos infos = new ChallengeInfos("11/1", "12/1", "1주 1회", 1, true);
            Challenge challenge = new Challenge(member, contents, infos);
            challenges.add(challenge);

            challengeRepository.save(challenge);
            memberChallengeRepository.save(new MemberChallenge(member, challenge));
        }

        // 0번째 친구에 인원 1 명 추가
        memberChallengeRepository.save(new MemberChallenge(member1, challenges.get(0)));

        // 1번째 친구에 인원 1 명 추가
        memberChallengeRepository.save(new MemberChallenge(member1, challenges.get(1)));

        // 2번째 친구에 인원 2 명 추가
        memberChallengeRepository.save(new MemberChallenge(member1, challenges.get(2)));
        memberChallengeRepository.save(new MemberChallenge(member2, challenges.get(2)));

        // 3번째 친구에 인원 2 명 추가
        memberChallengeRepository.save(new MemberChallenge(member1, challenges.get(3)));
        memberChallengeRepository.save(new MemberChallenge(member2, challenges.get(3)));

        // when
        GetPopularChallengesResponse response1 = challengeService.getPopularChallenges(member.getName(), 0, 2);
        GetPopularChallengesResponse response2 = challengeService.getPopularChallenges(member.getName(), 1, 2);
        GetPopularChallengesResponse response3 = challengeService.getPopularChallenges(member.getName(), 2, 2);

        // then
        // response의 결과가 size에 맞게 왔는지 확인한다.
        Assertions.assertThat(response1.getChallenges().size()).isEqualTo(2);
        Assertions.assertThat(response2.getChallenges().size()).isEqualTo(2);
        Assertions.assertThat(response3.getChallenges().size()).isEqualTo(1);

        // response1의 각 챌린지가 예상 챌린지인지 확인한다.
        Assertions.assertThat(response1.getChallenges().get(0).getMemberNum()).isEqualTo(3);
        Assertions.assertThat(response1.getChallenges().get(1).getMemberNum()).isEqualTo(3);

        // response2의 각 챌린지가 예상 챌린지인지 확인한다.
        Assertions.assertThat(response2.getChallenges().get(0).getMemberNum()).isEqualTo(2);
        Assertions.assertThat(response2.getChallenges().get(1).getMemberNum()).isEqualTo(2);

        // response3의 각 챌린지가 예상 챌린지인지 확인한다.
        Assertions.assertThat(response3.getChallenges().get(0).getMemberNum()).isEqualTo(1);
    }
}