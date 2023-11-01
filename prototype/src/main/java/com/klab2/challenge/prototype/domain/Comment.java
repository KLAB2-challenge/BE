package com.klab2.challenge.prototype.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "proof_post_id")
    private Long proofPostId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "comment")
    private String comment;

    public Comment(User user, ProofPost proofPost, String comment) {
        this.userName = user.getName();
        this.proofPostId = proofPost.getProofPostId();
        this.comment = comment;

        user.getComments().add(this);
        proofPost.getComments().add(this);
    }
}
