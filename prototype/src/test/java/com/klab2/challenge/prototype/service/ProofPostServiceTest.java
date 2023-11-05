package com.klab2.challenge.prototype.service;

import com.klab2.challenge.prototype.domain.*;
import com.klab2.challenge.prototype.dto.response.GetProofPostResponse;
import com.klab2.challenge.prototype.repository.ChallengeRepository;
import com.klab2.challenge.prototype.repository.MemberRepository;
import com.klab2.challenge.prototype.repository.ProofPostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class ProofPostServiceTest {

    @Autowired
    private ProofPostService proofPostService;
    @Autowired
    private ProofPostRepository proofPostRepository;
    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private MemberRepository memberRepository;

    private String memberName;
    private Long challengeId;
    private ChallengeContents contents;
    private ChallengeInfos infos;
    Member member;
    Challenge challenge;


    @BeforeEach
    public void beforeEach(){
         contents = new ChallengeContents("title", "image", "content");
         infos = new ChallengeInfos("11/1", "12/1", "1주 1회", 1, true);
         member = new Member("name");
         memberName = memberRepository.save(member).getName();
         challenge = new Challenge(member, contents, infos);
         challengeId = challengeRepository.save(challenge).getChallengeId();
    }

    @AfterEach
    public void afterEach(){
        proofPostRepository.deleteAll();
        challengeRepository.deleteAll();
    }

    @Test
    @DisplayName("챌린지에 인증글 등록하기")
    public void setProofPostTest() {
        //given
        ProofPostContents contents1 = new ProofPostContents("pp-title1","pp-content1","pp-image1");
        ProofPostContents contents2 = new ProofPostContents("pp-title2","pp-content2","pp-image2");


        //when
        long proofPostId1 = proofPostService.setProofPost(challengeId, memberName, contents1).getProofPostid();
        long proofPostId2 = proofPostService.setProofPost(challengeId, memberName, contents2).getProofPostid();

        //then
        List<ProofPost> found = proofPostRepository.findAll();
        Assertions.assertThat(found).hasSize(2);
        Assertions.assertThat(found.get(0).getProofPostId()).isEqualTo(proofPostId1);
    }

    @Test
    @DisplayName("챌린지에 인증글 가져오기")
    public void getProofPostsTest(){
        //given
        ProofPostContents proofPostContents = new ProofPostContents("test제목","인증글내용","이미지");
        ProofPost proofPost1 = new ProofPost(proofPostContents, challenge, member);
        ProofPost proofPost2 = new ProofPost(proofPostContents, challenge, member);
        ProofPost proofPost3 = new ProofPost(proofPostContents, challenge, member);
        proofPostRepository.save(proofPost1);
        proofPostRepository.save(proofPost2);
        proofPostRepository.save(proofPost3);

        //when
        List<GetProofPostResponse> responses = proofPostService.getProofPosts(challengeId,0, 2).getProofPosts();

        //then
        Assertions.assertThat(responses).hasSize(2);
    }

    @Test
    @DisplayName("챌린지에 모든 인증글 가져오기")
    public void getAllProofPostsTest(){
        //given
        ProofPostContents proofPostContents = new ProofPostContents("test제목","인증글내용","이미지");
        ProofPost proofPost1 = new ProofPost(proofPostContents, challenge, member);
        ProofPost proofPost2 = new ProofPost(proofPostContents, challenge, member);
        ProofPost proofPost3 = new ProofPost(proofPostContents, challenge, member);
        proofPostRepository.save(proofPost1);
        proofPostRepository.save(proofPost2);
        proofPostRepository.save(proofPost3);

        //when
        List<GetProofPostResponse> responses = proofPostService.getAllProofPosts(challengeId).getProofPosts();

        //then
        Assertions.assertThat(responses).hasSize(3);
    }

    @Test
    @DisplayName("특정 인증글 가져오기")
    public void getProofPostTest(){
        //given
        ProofPostContents proofPostContents = new ProofPostContents("test제목","인증글내용","이미지");
        ProofPost proofPost1 = new ProofPost(proofPostContents, challenge, member);
        Long proofPostId = proofPostRepository.save(proofPost1).getProofPostId();

        //when
        Long id = proofPostService.getProofPost(proofPostId).getProofPostId();

        //then
        Assertions.assertThat(id).isEqualTo(proofPostId);
    }
}