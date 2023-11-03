package com.klab2.challenge.prototype.controller;


import com.klab2.challenge.prototype.dto.request.SetCommentRequest;
import com.klab2.challenge.prototype.dto.response.GetAllCommentsResponse;
import com.klab2.challenge.prototype.dto.response.GetCommentResponse;
import com.klab2.challenge.prototype.dto.response.SetCommentResponse;
import com.klab2.challenge.prototype.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/setComment")
    public ResponseEntity<SetCommentResponse> setComment(@RequestBody @Valid SetCommentRequest request){
        SetCommentResponse response = commentService.setComment(request.getMemberName(), request.getMemberName(), request.getProofPostId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAllComments")
    public ResponseEntity<GetAllCommentsResponse> getAllComments(@RequestParam("proofPostId") Long proofPostId){
        GetAllCommentsResponse response = commentService.getAllComments(proofPostId);
        return ResponseEntity.ok(response);
    }
}
