package com.klab2.challenge.prototype.controller;

import com.klab2.challenge.prototype.dto.request.BuyBorderRequest;
import com.klab2.challenge.prototype.dto.response.BuyBorderResponse;
import com.klab2.challenge.prototype.service.MemberBorderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/memberBorder")
public class MemberBorderController {

    private final MemberBorderService memberBorderService;

    @PostMapping("/buyBorder")
    public ResponseEntity<BuyBorderResponse> buyBorder(@RequestBody @Valid BuyBorderRequest request){
        log.info("user: {}, request: /memberBorder/buyBorder", request.getMemberName());
        BuyBorderResponse response = memberBorderService.buyBorder(request.getMemberName(), request.getBorderId(), request.getCost());
        return ResponseEntity.ok(response);
    }
}
