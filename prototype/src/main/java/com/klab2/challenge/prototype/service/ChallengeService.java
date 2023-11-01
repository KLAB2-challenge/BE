package com.klab2.challenge.prototype.service;

import com.klab2.challenge.prototype.domain.Challenge;
import com.klab2.challenge.prototype.domain.ChallengeContents;
import com.klab2.challenge.prototype.domain.ChallengeInfos;
import com.klab2.challenge.prototype.domain.Member;
import com.klab2.challenge.prototype.dto.response.SetChallengeResponse;
import com.klab2.challenge.prototype.repository.ChallengeRepository;
import com.klab2.challenge.prototype.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public SetChallengeResponse setChallenge(String memberName, ChallengeContents contents, ChallengeInfos infos) {

        // 나중에 에러 처리해야함. (유저가 없는 경우)
        Member member = memberRepository.findByName(memberName).get();
        Challenge challenge = createChallenge(member, contents, infos);

        return new SetChallengeResponse(challenge.getChallengeId());
    }

    private Challenge createChallenge(Member member, ChallengeContents contents, ChallengeInfos infos) {
        Challenge challenge = new Challenge(member, contents, infos);
        return challengeRepository.save(challenge);
    }
}
