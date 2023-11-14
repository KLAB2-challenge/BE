package com.klab2.challenge.prototype.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor()
public class Border {

    @Id @GeneratedValue
    @Column(name = "border_id")
    private Long id;

    @OneToMany(mappedBy = "border")
    private List<MemberBorder> memberBorders = new ArrayList<>();
}