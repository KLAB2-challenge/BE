package com.klab2.challenge.prototype.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetProofPostResponse {
    private int proofPostId;
    private String userId;
    private String title;
    private String content;
    private String image;
    private int commentsNum;
}
