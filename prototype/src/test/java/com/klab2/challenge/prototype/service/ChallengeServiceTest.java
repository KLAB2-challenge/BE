package com.klab2.challenge.prototype.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.klab2.challenge.prototype.dto.response.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.klab2.challenge.prototype.domain.Challenge;
import com.klab2.challenge.prototype.domain.ChallengeContents;
import com.klab2.challenge.prototype.domain.ChallengeInfos;
import com.klab2.challenge.prototype.domain.Member;
import com.klab2.challenge.prototype.domain.MemberChallenge;
import com.klab2.challenge.prototype.repository.ChallengeRepository;
import com.klab2.challenge.prototype.repository.MemberChallengeRepository;
import com.klab2.challenge.prototype.repository.MemberRepository;
import org.springframework.web.multipart.MultipartFile;

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
    @DisplayName("(기본 이미지로) 챌린지를 정상적으로 생성한다.")
    public void setChallengeService() throws IOException {
        // given
        ChallengeContents contents = new ChallengeContents("title", "image", "content");
        ChallengeInfos infos = new ChallengeInfos("11/1", "12/1", "1주 1회", 1, true);
        MultipartFile image = null;

        // when
        Long challengeId = challengeService.setChallenge(member.getName(), contents, infos, image).getChallengeId();

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
        Long challengeId = challengeRepository.save(challenge).getChallengeId();

        memberChallengeRepository.save(new MemberChallenge(member1, challenge));
        memberChallengeRepository.save(new MemberChallenge(member2, challenge));

        // when
        GetChallengeResponse response1 = challengeService.getChallenge(member.getName(), challenge.getChallengeId());
        GetChallengeResponse response2 = challengeService.getChallenge(member1.getName(), challenge.getChallengeId());

        // then
        Assertions.assertThat(response1.getChallengeId()).isEqualTo(challengeId);
        Assertions.assertThat(response1.getMemberNum()).isEqualTo(2);
        Assertions.assertThat(response1.isJoin()).isEqualTo(false);
        Assertions.assertThat(response2.isJoin()).isEqualTo(true);
    }

    @Test
    @DisplayName("인기 챌린지를 가져온다.")
    public void getPopularChallengesService() {
        // given
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Challenge> challenges = createChallenges(5, true, 0);

        // 0번째 챌린지에 인원 1 명 추가
        joinChallenge(member1, challenges.get(0));

        // 1번째 챌린지에 인원 1 명 추가
        joinChallenge(member1, challenges.get(1));

        // 2번째 챌린지에 인원 2 명 추가
        joinChallenge(member1, challenges.get(2));
        joinChallenge(member2, challenges.get(2));

        // 3번째 챌린지에 인원 2 명 추가
        joinChallenge(member1, challenges.get(3));
        joinChallenge(member2, challenges.get(3));

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

    @Test
    @DisplayName("공식/유저 챌린지를 가져온다.")
    public void getOfficialChallengesService() {
        // given
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Challenge> officialChallenges = createChallenges(3, true, 0);
        List<Challenge> userChallenges = createChallenges(2, false, 0);

        // when
        GetOfficialOrUserChallengesResponse response1 =
                challengeService.getOfficialOrUserChallenges(member.getName(), 0, 5, true);
        GetOfficialOrUserChallengesResponse response2 =
                challengeService.getOfficialOrUserChallenges(member.getName(), 0, 5, false);

        // then
        Assertions.assertThat(response1.getChallenges().size()).isEqualTo(3);
        Assertions.assertThat(response1.getChallenges().get(0).getInfos().getType()).isEqualTo(true);
        Assertions.assertThat(response1.getChallenges().get(1).getInfos().getType()).isEqualTo(true);
        Assertions.assertThat(response1.getChallenges().get(2).getInfos().getType()).isEqualTo(true);

        Assertions.assertThat(response2.getChallenges().size()).isEqualTo(2);
        Assertions.assertThat(response2.getChallenges().get(0).getInfos().getType()).isEqualTo(false);
        Assertions.assertThat(response2.getChallenges().get(1).getInfos().getType()).isEqualTo(false);
    }

    @Test
    @DisplayName("관련 챌린지를 가져온다.")
    public void getRelatedChallengesService() {
        // given
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Challenge> Challenges1 = createChallenges(3, true, 1);
        List<Challenge> Challenges2 = createChallenges(2, false, 2);

        // when
        GetRelatedChallengesResponse response1 =
                challengeService.getRelatedChallenges(member.getName(), 0, 5, 1);
        GetRelatedChallengesResponse response2 =
                challengeService.getRelatedChallenges(member.getName(), 0, 5, 2);

        // then
        Assertions.assertThat(response1.getChallenges().size()).isEqualTo(3);
        Assertions.assertThat(response2.getChallenges().size()).isEqualTo(2);
    }

    private List<Challenge> createChallenges(int num, boolean type, int category) {
        List<Challenge> challenges = new ArrayList<>();

        for(int i = 0; i < num; i++) {
            ChallengeContents contents = new ChallengeContents("title"+i, "image", "content");
            ChallengeInfos infos = new ChallengeInfos("11/1", "12/1", "1주 1회", category, type);
            Challenge challenge = new Challenge(member, contents, infos);
            challenges.add(challenge);

            challengeRepository.save(challenge);
            memberChallengeRepository.save(new MemberChallenge(member, challenge));
        }

        return challenges;
    }

    private void joinChallenge(Member member, Challenge challenge) {
        memberChallengeRepository.save(new MemberChallenge(member, challenge));
    }

    @Test
    @DisplayName("유저가 참여중인 챌린지를 가져온다.")
    public void getMemberAllChallenges() {
        // given
        createChallenges(3, true, 1);

        // when
        GetMemberAllChallengesResponse response =
                challengeService.getMemberAllChallenges(member.getName(), 0, 5);

        // then
        Assertions.assertThat(response.getChallenges().size()).isEqualTo(3);
    }
}