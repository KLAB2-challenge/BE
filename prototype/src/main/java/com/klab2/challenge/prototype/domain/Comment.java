package com.klab2.challenge.prototype.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proof_post_id")
    private ProofPost proofPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "comment")
    private String comment;

    public Comment(Member member, ProofPost proofPost, String comment) {
        this.member = member;
        this.proofPost = proofPost;
        this.comment = comment;

        member.getComments().add(this);
        proofPost.getComments().add(this);
    }
}
