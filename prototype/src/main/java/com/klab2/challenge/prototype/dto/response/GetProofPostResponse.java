package com.klab2.challenge.prototype.dto.response;

import com.klab2.challenge.prototype.domain.ProofPostContents;
import com.klab2.challenge.prototype.domain.ProofPostInfos;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetProofPostResponse {
    private Long proofPostId;
    private String memberName;
    private ProofPostContents contents;
    private ProofPostInfos infos;
    private int commentNum;
}
