package com.klab2.challenge.prototype.controller;


import com.klab2.challenge.prototype.dto.request.SetCommentRequest;
import com.klab2.challenge.prototype.dto.response.GetAllCommentsResponse;
import com.klab2.challenge.prototype.dto.response.GetCommentResponse;
import com.klab2.challenge.prototype.dto.response.SetCommentResponse;
import com.klab2.challenge.prototype.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/setComment")
    public ResponseEntity<SetCommentResponse> setComment(@RequestBody @Valid SetCommentRequest request){
        log.info("user: {}, request: /comment/setComment", request.getMemberName());
        SetCommentResponse response = commentService.setComment(request.getMemberName(), request.getContent(), request.getProofPostId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAllComments")
    public ResponseEntity<GetAllCommentsResponse> getAllComments(@RequestParam("proofPostId") Long proofPostId){
        log.info("user: Unknown, request: /comment/setComment");
        GetAllCommentsResponse response = commentService.getAllComments(proofPostId);
        return ResponseEntity.ok(response);
    }
}
