package com.klab2.challenge.prototype.service;

import com.klab2.challenge.prototype.domain.*;
import com.klab2.challenge.prototype.dto.response.*;
import com.klab2.challenge.prototype.repository.ChallengeRepository;
import com.klab2.challenge.prototype.repository.MemberChallengeRepository;
import com.klab2.challenge.prototype.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        Challenge challenge = new Challenge(member, contents, infos);

        challengeRepository.save(challenge);
        memberChallengeRepository.save(new MemberChallenge(member, challenge));

        return new SetChallengeResponse(challenge.getChallengeId());
    }

    @Transactional(readOnly = true)
    public GetChallengeResponse getChallenge(String memberName, Long challengeId) {

        // 나중에 유저가 있는지 없는지 확인하는 용도로 memberName을 사용할 것. 지금은 그냥 씀.
        Member member = memberRepository.findByName(memberName).get();

        // challengeId를 사용해, 해당 챌린지의 모든 멤버의 개수(오너 제외)를 가져옴. 오너는 response 객체를 만들때 추가함.
        int memberNum = Integer.parseInt(memberChallengeRepository
                .findMemberNumOfChallenge(challengeId)
                .toString());
        // challengeId를 사용해, 챌린지를 가져옴. (나중에 해당 id의 챌린지가 있는지 검증해야함.)
        Challenge challenge = challengeRepository.findById(challengeId).get();

        // 유저가 챌린지에 참가 중인지 확인
        boolean isJoin = memberChallengeRepository.findMemberChallengeByMemberAndChallenge(member, challenge).isPresent();

        return new GetChallengeResponse(challenge.getContents(), challenge.getInfos(), memberNum, isJoin);
    }

    @Transactional(readOnly = true)
    public GetPopularChallengesResponse getPopularChallenges(String memberName, int page, int size) {

        // 나중에 유저가 있는지 없는지 확인하는 용도로 memberName을 사용할 것. 지금은 그냥 씀.
        Member member = memberRepository.findByName(memberName).get();

        PageRequest pageRequest = PageRequest.of(page, size);

        List<GetChallengeResponse> getChallengeResponses =
                challengeRepository.getPopularChallenges(pageRequest)
                        .stream()
                        .map( challenge -> {
                            return new GetChallengeResponse(
                                challenge.getContents(),
                                    challenge.getInfos(),
                                    Integer.parseInt(memberChallengeRepository
                                            .findMemberNumOfChallenge(challenge.getChallengeId())
                                            .toString()),
                                    memberChallengeRepository
                                            .findMemberChallengeByMemberAndChallenge(member, challenge)
                                            .isPresent()
                            );
                        })
                        .toList();

        return new GetPopularChallengesResponse(getChallengeResponses);
    }

    @Transactional(readOnly = true)
    public GetOfficialOrUserChallengesResponse getOfficialOrUserChallenges(String memberName, int page, int size, boolean type) {

        // 나중에 유저가 있는지 없는지 확인하는 용도로 memberName을 사용할 것. 지금은 그냥 씀.
        Member member = memberRepository.findByName(memberName).get();

        PageRequest pageRequest = PageRequest.of(page, size);

        List<GetChallengeResponse> getChallengeResponses =
                challengeRepository.getOfficialOrChallenges(type, pageRequest)
                        .stream()
                        .map( challenge -> {
                            return new GetChallengeResponse(
                                    challenge.getContents(),
                                    challenge.getInfos(),
                                    Integer.parseInt(memberChallengeRepository
                                            .findMemberNumOfChallenge(challenge.getChallengeId())
                                            .toString()),
                                    memberChallengeRepository
                                            .findMemberChallengeByMemberAndChallenge(member, challenge)
                                            .isPresent()
                            );
                        })
                        .toList();

        return new GetOfficialOrUserChallengesResponse(getChallengeResponses);
    }

    @Transactional(readOnly = true)
    public GetRelatedChallengesResponse getRelatedChallenges(String memberName, int page, int size, int category) {

        // 나중에 유저가 있는지 없는지 확인하는 용도로 memberName을 사용할 것. 지금은 그냥 씀.
        Member member = memberRepository.findByName(memberName).get();

        PageRequest pageRequest = PageRequest.of(page, size);

        List<GetChallengeResponse> getChallengeResponses =
                challengeRepository.getRelatedChallenges(category, pageRequest)
                        .stream()
                        .map( challenge -> {
                            return new GetChallengeResponse(
                                    challenge.getContents(),
                                    challenge.getInfos(),
                                    Integer.parseInt(memberChallengeRepository
                                            .findMemberNumOfChallenge(challenge.getChallengeId())
                                            .toString()),
                                    memberChallengeRepository
                                            .findMemberChallengeByMemberAndChallenge(member, challenge)
                                            .isPresent()
                            );
                        })
                        .toList();

        return new GetRelatedChallengesResponse(getChallengeResponses);
    }
}
