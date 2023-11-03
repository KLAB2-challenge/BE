package com.klab2.challenge.prototype.service;

import com.klab2.challenge.prototype.domain.*;
import com.klab2.challenge.prototype.dto.response.GetCommentResponse;
import com.klab2.challenge.prototype.repository.ChallengeRepository;
import com.klab2.challenge.prototype.repository.CommentRepository;
import com.klab2.challenge.prototype.repository.MemberRepository;
import com.klab2.challenge.prototype.repository.ProofPostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private ProofPostRepository proofPostRepository;
    @Autowired
    private CommentRepository commentRepository;

    Member member;;
    Challenge challenge;
    ProofPost proofPost;
    Long proofPostId;

    @BeforeEach
    public void beforeEach() {
        ChallengeContents challengeContents = new ChallengeContents("title", "image", "content");
        ChallengeInfos infos = new ChallengeInfos("11/1", "12/1", "1주 1회", 1, true);
        ProofPostContents proofPostContents = new ProofPostContents("title", "cotent", "image");

        member = new Member("member");;
        challenge = new Challenge(member, challengeContents, infos);
        proofPost = new ProofPost(proofPostContents, challenge, member);

        memberRepository.save(member);
        challengeRepository.save(challenge);
        proofPostId = proofPostRepository.save(proofPost).getProofPostId();
    }

    @AfterEach
    public void afterEach() {
        commentRepository.deleteAll();
        proofPostRepository.deleteAll();
        challengeRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("Comment를 성공적으로 저장한다.")
    public void setCommentService() {
        // when
        Long commentId = commentService.setComment(member.getName(), "content", proofPostId).getCommentId();

        //then
        Assertions.assertThat(commentId).isEqualTo(proofPostId);
    }

    @Test
    @DisplayName("Comment를 모두 가져온다.")
    public void getAllCommentsService() {
        // given
        Comment comment1 = new Comment(member, proofPost, "content");
        Comment comment2 = new Comment(member, proofPost, "content");
        commentRepository.save(comment1);
        commentRepository.save(comment2);

        // when
        List<GetCommentResponse> responses = commentService.getAllComments(proofPostId).getGetCommentResponses();

        // then
        Assertions.assertThat(responses.size()).isEqualTo(2);
    }
}
