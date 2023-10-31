package com.klab2.challenge.prototype.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "pp_id")
    private Long pp_id;

    @Column(name = "comment")
    private String comment;

    public void setComment(String comment){
        this.comment = comment;
    }


}
