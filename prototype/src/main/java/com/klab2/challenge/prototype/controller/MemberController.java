package com.klab2.challenge.prototype.controller;

import com.klab2.challenge.prototype.dto.request.ChangeCurrentBorderRequest;
import com.klab2.challenge.prototype.dto.request.GetMemberInfosRequest;
import com.klab2.challenge.prototype.dto.request.SetMemberCoinsRequest;
import com.klab2.challenge.prototype.dto.response.ChangeCurrentBorderResponse;
import com.klab2.challenge.prototype.dto.response.GetMemberInfosResponse;
import com.klab2.challenge.prototype.dto.response.GetRankResponse;
import com.klab2.challenge.prototype.dto.response.SetMemberCoinsResponse;
import com.klab2.challenge.prototype.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/setMemberCoin")
    public ResponseEntity<SetMemberCoinsResponse> setMemberCoins(@RequestBody @Valid SetMemberCoinsRequest request){
        SetMemberCoinsResponse response = memberService.setMemberCoins(request.getMemberName(), request.getCoins());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getMemberInfos")
    public ResponseEntity<GetMemberInfosResponse> getMemberInfos(@RequestBody @Valid GetMemberInfosRequest request){
        GetMemberInfosResponse response = memberService.getMemberInfos(request.getMemberName());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/changeCurrentBorder")
    public ResponseEntity<ChangeCurrentBorderResponse> changeCurrentBorder(@RequestBody @Valid ChangeCurrentBorderRequest request){
        ChangeCurrentBorderResponse response = memberService.changeCurrentBorder(request.getMemberName(), request.getBorderId());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getRating")
    public ResponseEntity<GetRankResponse> getRanking(@RequestParam("memberName") String memberName){
        GetRankResponse response = memberService.getRank(memberName);
        return  ResponseEntity.ok(response);
    }

}