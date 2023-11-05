package com.klab2.challenge.prototype.service;

import com.klab2.challenge.prototype.domain.*;
import com.klab2.challenge.prototype.dto.response.GetAllCommentsResponse;
import com.klab2.challenge.prototype.dto.response.GetCommentResponse;
import com.klab2.challenge.prototype.dto.response.SetCommentResponse;
import com.klab2.challenge.prototype.repository.CommentRepository;
import com.klab2.challenge.prototype.repository.MemberRepository;
import com.klab2.challenge.prototype.repository.ProofPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final MemberRepository memberRepository;
    private final ProofPostRepository proofPostRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public SetCommentResponse setComment(String memberName, String content, Long proofPostId) {
        Member member = memberRepository.findByName(memberName).get();
        ProofPost proofPost = proofPostRepository.findById(proofPostId).get();

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String formattedDate = simpleDateFormat.format(date);
        CommentInfos infos = new CommentInfos(formattedDate);

        Long commentId = commentRepository.save(new Comment(member, proofPost, content, infos)).getCommentId();

        return new SetCommentResponse(commentId);
    }

    @Transactional(readOnly = true)
    public GetAllCommentsResponse getAllComments(Long proofPostId) {
        List<GetCommentResponse> getCommentResponses =
                commentRepository.findAllCommentsByProofPost(proofPostId)
                .stream()
                .map(comment -> {
                    return new GetCommentResponse(
                            comment.getMember().getName(),
                            comment.getContent(),
                            comment.getInfos()
                    );
                })
                .toList();

        return new GetAllCommentsResponse(getCommentResponses);
    }
}
