package com.klab2.challenge.prototype.service;

import com.klab2.challenge.prototype.domain.Member;
import com.klab2.challenge.prototype.dto.response.ChangeCurrentBorderResponse;
import com.klab2.challenge.prototype.dto.response.GetMemberInfosResponse;
import com.klab2.challenge.prototype.dto.response.GetRankResponse;
import com.klab2.challenge.prototype.dto.response.SetMemberCoinsResponse;
import com.klab2.challenge.prototype.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public SetMemberCoinsResponse setMemberCoins(String memberName, int coins) {
        Member member = memberRepository.findByName(memberName).get();

        int totalCoins = member.getInfos().getTotalCoins()+coins;
        int holdingCoins = member.getInfos().getHoldingCoins()+coins;

        memberRepository.setMemberCoins(member.getMemberId(), totalCoins, holdingCoins);

        return new SetMemberCoinsResponse(true);
    }

    @Transactional
    public ChangeCurrentBorderResponse changeCurrentBorder(String memberName, Long borderId) {
        Member member = memberRepository.findByName(memberName).get();
        memberRepository.changeCurrentBorder(member.getMemberId(), borderId);

        return new ChangeCurrentBorderResponse(true);
    }

    @Transactional(readOnly = true)
    public GetMemberInfosResponse getMemberInfos(String memberName) {
        Member member = memberRepository.findByName(memberName).get();

        return new GetMemberInfosResponse(member.getName(), member.getInfos());
    }

    @Transactional(readOnly = true)
    public GetRankResponse getRank(String memberName){
        Member member = memberRepository.findByName(memberName).get();
        int myRank = memberRepository.findMyRankByName(member.getInfos().getTotalCoins()).get()+1;
        Sort sort = Sort.by("infos.holdingCoins").descending();
        PageRequest pageRequest = PageRequest.of(0,20,sort);
        Page<Member> memberEntities = memberRepository.findAll(pageRequest);
        List<Member> ranker = memberEntities.stream().toList();
        return new GetRankResponse(myRank,ranker);
    }

}
