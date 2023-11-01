package com.klab2.challenge.prototype.service;

import com.klab2.challenge.prototype.domain.Challenge;
import com.klab2.challenge.prototype.domain.ChallengeContents;
import com.klab2.challenge.prototype.domain.ChallengeInfos;
import com.klab2.challenge.prototype.domain.Member;
import com.klab2.challenge.prototype.dto.response.GetChallengeResponse;
import com.klab2.challenge.prototype.dto.response.GetSomePopularChallengesResponse;
import com.klab2.challenge.prototype.dto.response.SetChallengeResponse;
import com.klab2.challenge.prototype.repository.ChallengeRepository;
import com.klab2.challenge.prototype.repository.MemberChallengeRepository;
import com.klab2.challenge.prototype.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final MemberRepository memberRepository;
    private final MemberChallengeRepository memberChallengeRepository;

    @Transactional
    public SetChallengeResponse setChallenge(String memberName, ChallengeContents contents, ChallengeInfos infos) {

        // 나중에 에러 처리해야함. (유저가 없는 경우)
        Member member = memberRepository.findByName(memberName).get();
        Challenge challenge = createChallenge(member, contents, infos);

        return new SetChallengeResponse(challenge.getChallengeId());
    }

    @Transactional(readOnly = true)
    public GetChallengeResponse getChallenge(String memberName, Long challengeId) {

        // 나중에 유저가 있는지 없는지 확인하는 용도로 memberName을 사용할 것. 지금은 그냥 씀.
        Member member = memberRepository.findByName(memberName).get();

        // challengeId를 사용해, 해당 챌린지의 모든 멤버의 개수(오너 제외)를 가져옴. 오너는 response 객체를 만들때 추가함.
        Integer memberNum = Integer.parseInt(memberChallengeRepository.findMemberNumOfChallenge(challengeId).toString());
        // challengeId를 사용해, 챌린지를 가져옴. (나중에 해당 id의 챌린지가 있는지 검증해야함.)
        Challenge challenge = challengeRepository.findById(challengeId).get();

        return new GetChallengeResponse(challenge.getContents(), challenge.getInfos(), memberNum+1);
    }

    private Challenge createChallenge(Member member, ChallengeContents contents, ChallengeInfos infos) {
        Challenge challenge = new Challenge(member, contents, infos);
        return challengeRepository.save(challenge);
    }
}
