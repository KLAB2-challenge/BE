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

    @Id @GeneratedValue()
    @Column(name="proof_post_id")
    private Long proofPostId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Challenge challenge;

    @OneToMany(mappedBy = "comment")
    private List<Comment> comments = new ArrayList<>();

    @Column(name = "user_id", nullable = false)
    private long userID;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "image")
    private String image;
}
