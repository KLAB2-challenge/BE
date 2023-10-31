package com.klab2.challenge.prototype.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCommentsRequest {
    private Long proofPostId;
    private int startPoint;
}
