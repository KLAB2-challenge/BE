package com.klab2.challenge.prototype.service;

import com.klab2.challenge.prototype.domain.*;
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
    @DisplayName("챌린지에 인증글을 등록하기")
    public void setProofPostTest() {
        //given

        //when
        long proofPostId1 = proofPostService.setProofPost(challengeId,memberName,"pp-title","pp-content","pp-image").getProofPostid();
        long proofPostId2 = proofPostService.setProofPost(challengeId,memberName,"pp-title2","pp-content2","pp-image2").getProofPostid();
        //then
        List<ProofPost> found = proofPostRepository.findAll();
        Assertions.assertThat(found).hasSize(2);
        Assertions.assertThat(found.get(0).getProofPostId()).isEqualTo(proofPostId1);
    }

    @Test
    @DisplayName("챌린지에 인증글을 가져오기")
    public void getProofPostTest(){
        //given
        ProofPostContents proofPostContents = new ProofPostContents("test제목","인증글내용","이미지");
        ProofPost proofPost1 = new ProofPost(proofPostContents, challenge, member);
        ProofPost proofPost2 = new ProofPost(proofPostContents, challenge, member);
        long proofPostId1 = proofPostRepository.save(proofPost1).getProofPostId();
        long proofPostId2 = proofPostRepository.save(proofPost2).getProofPostId();
        //when
        List<ProofPost> proofPosts = proofPostService.getProofPosts(challengeId,2).getProofPosts();
        //then
        System.out.println(proofPosts.size());
        //Assertions.assertThat().hasSize(2);
        Assertions.assertThat(proofPosts.get(0).getProofPostId()).isEqualTo(proofPostId1);
        Assertions.assertThat(proofPosts.get(1).getProofPostId()).isEqualTo(proofPostId2);

    }
}
