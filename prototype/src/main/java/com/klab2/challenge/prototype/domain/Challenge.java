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

    @Embedded
    @Column(name = "contents")
    private ChallengeContents contents;

    @Embedded
    @Column(name = "infos")
    private ChallengeInfos infos;

    @OneToMany(mappedBy = "user")
    private List<UserChallenge> userChallenges = new ArrayList<>();

    @OneToMany(mappedBy = "proofPost")
    private List<ProofPost> proofPosts = new ArrayList<>();

    public Challenge(ChallengeContents contents, ChallengeInfos infos) {
        this.contents = contents;
        this.infos = infos;
    }
}
