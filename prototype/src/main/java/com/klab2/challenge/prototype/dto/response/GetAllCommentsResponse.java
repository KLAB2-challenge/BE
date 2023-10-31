package com.klab2.challenge.prototype.dto.response;

import com.klab2.challenge.prototype.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetAllCommentsResponse {
    int count;
    List<Comment> commentList;
}
