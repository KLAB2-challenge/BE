package com.klab2.challenge.prototype.service;

import com.klab2.challenge.prototype.domain.Challenge;
import com.klab2.challenge.prototype.domain.ChallengeContents;
import com.klab2.challenge.prototype.domain.ChallengeInfos;
import com.klab2.challenge.prototype.domain.Member;
import com.klab2.challenge.prototype.repository.ChallengeRepository;
import com.klab2.challenge.prototype.repository.MemberChallengeRepository;
import com.klab2.challenge.prototype.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberChallengeServiceTest {

    @Autowired
    private MemberChallengeService memberChallengeService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private MemberChallengeRepository memberChallengeRepository;

    @AfterEach
    public void afterEach() {
        memberChallengeRepository.deleteAll();
        challengeRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("새로운 유저를 챌린지에 등록한다.")
    public void joinChallengeService() {

        Member member = new Member("member");
        memberRepository.save(member);

        ChallengeContents contents = new ChallengeContents("title", "image", "content");
        ChallengeInfos infos = new ChallengeInfos("11/1", "12/1", "1주 1회", 1, true);
        Challenge challenge = new Challenge(member, contents, infos);
        challengeRepository.save(challenge);

        Long challengeId = memberChallengeService.joinChallenge(member.getName(), challenge.getChallengeId()).getChallengeId();

        Assertions.assertThat(challengeId).isEqualTo(challenge.getChallengeId());
    }
}
