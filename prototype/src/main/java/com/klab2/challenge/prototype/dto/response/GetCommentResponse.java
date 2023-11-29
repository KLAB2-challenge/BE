package com.klab2.challenge.prototype.dto.response;

import com.klab2.challenge.prototype.domain.Comment;
import com.klab2.challenge.prototype.domain.CommentInfos;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetCommentResponse {
    private String memberName;
    private Long memberCurrentBorder;
    private String memberImageUrl;
    private String content;
    private CommentInfos infos;
}
