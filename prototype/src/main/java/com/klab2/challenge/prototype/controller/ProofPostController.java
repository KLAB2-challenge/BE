package com.klab2.challenge.prototype.controller;

import com.klab2.challenge.prototype.service.ProofPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/proofPost")
public class ProofPostController {

    private final ProofPostService proofPostService;


}
