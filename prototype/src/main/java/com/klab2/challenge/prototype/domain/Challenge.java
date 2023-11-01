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
public class Challenge {

    @Id @GeneratedValue
    @Column(name = "challenge_id")
    private Long challengeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Embedded
    @Column(name = "contents")
    private ChallengeContents contents;

    @Embedded
    @Column(name = "infos")
    private ChallengeInfos infos;

    @OneToMany(mappedBy = "challenge")
    private List<MemberChallenge> memberChallenges = new ArrayList<>();

    @OneToMany(mappedBy = "challenge")
    private List<ProofPost> proofPosts = new ArrayList<>();

    public Challenge(Member member, ChallengeContents contents, ChallengeInfos infos) {
        this.member = member;
        this.contents = contents;
        this.infos = infos;
    }
}
