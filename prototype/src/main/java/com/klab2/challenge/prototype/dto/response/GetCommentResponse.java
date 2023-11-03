package com.klab2.challenge.prototype.dto.response;

import com.klab2.challenge.prototype.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetCommentResponse {
    String memberName;
    String content;
}
