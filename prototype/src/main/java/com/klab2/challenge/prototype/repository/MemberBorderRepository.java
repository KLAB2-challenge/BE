package com.klab2.challenge.prototype.repository;

import com.klab2.challenge.prototype.domain.Border;
import com.klab2.challenge.prototype.domain.Member;
import com.klab2.challenge.prototype.domain.MemberBorder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberBorderRepository extends JpaRepository<MemberBorder, Long> {

    @Query("SELECT mb.border.id FROM MemberBorder mb " +
            "WHERE mb.member.memberId = :memberId")
    List<Long> getMemberAllBorders(@Param("memberId") Long memberId);

    Optional<MemberBorder> findMemberBorderByMemberAndBorder(Member member, Border border);
}
