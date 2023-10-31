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

    @Id @GeneratedValue()
    @Column(name = "challenge_id")
    private Long challengeId;

    @Column()
    private String title;

    @Column()
    private String image;

    @Column()
    private String startDate;

    @Column()
    private String endDate;

    @Column()
    private String frequency;

    @Column()
    private Integer category;

    @Column()
    private Boolean type;

    @OneToMany(mappedBy = "user")
    private List<UserChallenge> userChallenges = new ArrayList<>();
}
