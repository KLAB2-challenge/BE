package com.klab2.challenge.prototype.controller;


import com.klab2.challenge.prototype.dto.response.GetAllCommentsResponse;
import com.klab2.challenge.prototype.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/getAllComments")
    @ResponseBody
    public ResponseEntity<GetAllCommentsResponse> getAllComments(){
        //GetAllCommentsResponse response = commentService;
    }
}
