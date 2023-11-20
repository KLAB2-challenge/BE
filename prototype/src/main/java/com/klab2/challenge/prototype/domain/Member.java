package com.klab2.challenge.prototype.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    @Column(name = "infos")
    private MemberInfos infos;

    @OneToMany(mappedBy = "member")
    private List<Challenge> challenges = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberChallenge> memberChallenges = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<ProofPost> proofPosts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberBorder> memberBorders = new ArrayList<>();

    public Member(String name) {
        this.name = name;
        this.infos = new MemberInfos(0, 0, 1L,
                "https://klab2-challenge-app.s3.ap-northeast-2.amazonaws.com/userImages/defaultUserImage.png");
    }

    public Member(String name, String image) {
        this.name = name;
        this.infos = new MemberInfos(0, 0, 1L, image);
    }
}
