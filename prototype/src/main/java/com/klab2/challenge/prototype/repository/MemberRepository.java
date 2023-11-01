package com.klab2.challenge.prototype.repository;

import com.klab2.challenge.prototype.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m FROM Member m " +
            "WHERE m.name = :memberName " +
            "ORDER BY m.memberId desc " +
            "LIMIT 1")
    Optional<Member> findByName(@Param("memberName") String name);
}
