package com.klab2.challenge.prototype.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserChallenge {
    @Id @GeneratedValue()
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Challenge challenge;

    public UserChallenge(User user, Challenge challenge) {
        addUser(user);
        addChallenge(challenge);
    }

    private void addUser(User user) {
        this.user = user;
        user.getUserChallenges().add(this);
    }

    private void addChallenge(Challenge challenge) {
        this.challenge = challenge;
        challenge.getUserChallenges().add(this);
    }
}
