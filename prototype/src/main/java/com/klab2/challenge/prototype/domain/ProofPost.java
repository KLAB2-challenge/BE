package com.klab2.challenge.prototype.domain;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProofPost {

    @Id @GeneratedValue
    @Column(name="proof_post_id")
    private Long proofPostId;

    @Embedded
    @Column(name = "contents")
    private ProofPostContents contents;

    @Embedded
    @Column(name = "infos")
    private ProofPostInfos infos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "proofPost")
    private List<Comment> comments = new ArrayList<>();

    public ProofPost(Challenge challenge, Member member, ProofPostContents contents, ProofPostInfos infos) {
        this.member = member;
        this.challenge = challenge;
        this.contents = contents;
        this.infos = infos;

        challenge.getProofPosts().add(this);
        member.getProofPosts().add(this);
    }
}
