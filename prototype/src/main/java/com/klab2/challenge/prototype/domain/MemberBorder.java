package com.klab2.challenge.prototype.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberBorder {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "border_id")
    private Border border;

    public MemberBorder(Member member, Border border) {
        this.member = member;
        this.border = border;

        border.getMemberBorders().add(this);
        member.getMemberBorders().add(this);
    }
}