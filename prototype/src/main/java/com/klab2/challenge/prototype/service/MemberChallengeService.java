package com.klab2.challenge.prototype.service;

import com.klab2.challenge.prototype.domain.Challenge;
import com.klab2.challenge.prototype.domain.Member;
import com.klab2.challenge.prototype.domain.MemberChallenge;
import com.klab2.challenge.prototype.dto.response.JoinChallengeResponse;
import com.klab2.challenge.prototype.repository.ChallengeRepository;
import com.klab2.challenge.prototype.repository.MemberChallengeRepository;
import com.klab2.challenge.prototype.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberChallengeService {

    private final MemberChallengeRepository memberChallengeRepository;
    private final MemberRepository memberRepository;
    private final ChallengeRepository challengeRepository;

    @Transactional
    public JoinChallengeResponse joinChallenge(String memberName, Long challengeId) {
        // 나중에 예외 처리할 때, member가 있는지, 챌린지가 있는지 체크해야함.

        Member member = memberRepository.findByName(memberName).get();
        Challenge challenge = challengeRepository.findById(challengeId).get();
        memberChallengeRepository.save(new MemberChallenge(member, challenge));

        return new JoinChallengeResponse(challenge.getChallengeId());
    }
}
