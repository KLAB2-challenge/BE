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

    @OneToMany(mappedBy = "comment")
    private List<Comment> comments = new ArrayList<>();

    public ProofPost(ProofPostContents contents, ProofPostInfos infos, Challenge challenge, User user) {
        this.contents = contents;
        this.infos = infos;

        challenge.getProofPosts().add(this);
        user.getProofPosts().add(this);
    }
}
