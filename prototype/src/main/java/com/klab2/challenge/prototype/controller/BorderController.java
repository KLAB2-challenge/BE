package com.klab2.challenge.prototype.controller;

import com.klab2.challenge.prototype.dto.request.GetMemberAllBordersRequest;
import com.klab2.challenge.prototype.dto.response.GetMemberAllBordersResponse;
import com.klab2.challenge.prototype.service.BorderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/border")
public class BorderController {

    private final BorderService borderService;

    @PostMapping("getMemberAllBorders")
    public ResponseEntity<GetMemberAllBordersResponse> getMemberAllBorders(@RequestBody @Valid GetMemberAllBordersRequest request) {
        GetMemberAllBordersResponse response = borderService.getMemberAllBorders(request.getMemberName());
        return ResponseEntity.ok(response);
    }
}
