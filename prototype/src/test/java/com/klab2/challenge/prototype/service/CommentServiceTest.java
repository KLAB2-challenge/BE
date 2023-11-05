package com.klab2.challenge.prototype.service;

import com.klab2.challenge.prototype.domain.*;
import com.klab2.challenge.prototype.dto.response.GetCommentResponse;
import com.klab2.challenge.prototype.repository.ChallengeRepository;
import com.klab2.challenge.prototype.repository.CommentRepository;
import com.klab2.challenge.prototype.repository.MemberRepository;
import com.klab2.challenge.prototype.repository.ProofPostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    private Member member;;
    private Challenge challenge;
    private ProofPost proofPost;
    private Long proofPostId;
    private static ProofPostInfos proofPostInfos;
    private static CommentInfos commentInfos;


    @BeforeAll
    static void beforeAll() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String formattedDate = simpleDateFormat.format(date);

        proofPostInfos = new ProofPostInfos(formattedDate);
        commentInfos = new CommentInfos(formattedDate);
    }

    @BeforeEach
    public void beforeEach() {
        ChallengeContents challengeContents = new ChallengeContents("title", "image", "content");
        ChallengeInfos infos = new ChallengeInfos("11/1", "12/1", "1주 1회", 1, true);
        ProofPostContents proofPostContents = new ProofPostContents("title", "cotent", "image");

        member = new Member("member");;
        challenge = new Challenge(member, challengeContents, infos);
        proofPost = new ProofPost(challenge, member, proofPostContents, proofPostInfos);

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
        Date date = new Date();

        Comment comment1 = new Comment(member, proofPost, "content", commentInfos);
        Comment comment2 = new Comment(member, proofPost, "content", commentInfos);
        commentRepository.save(comment1);
        commentRepository.save(comment2);

        // when
        List<GetCommentResponse> responses = commentService.getAllComments(proofPostId).getGetCommentResponses();

        // then
        Assertions.assertThat(responses.size()).isEqualTo(2);
    }
}
