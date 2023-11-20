package com.klab2.challenge.prototype.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeContents {

    private String title;
    private String content;
    private String image;

    public void setImage(String image) {
        this.image = image;
    }
}
