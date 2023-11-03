package com.klab2.challenge.prototype.repository;

import com.klab2.challenge.prototype.domain.Challenge;
import com.klab2.challenge.prototype.domain.Member;
import com.klab2.challenge.prototype.domain.MemberChallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberChallengeRepository extends JpaRepository<MemberChallenge, Long> {

    @Query("SELECT COUNT(*) AS member_num " +
            "FROM MemberChallenge mc " +
            "WHERE mc.challenge.challengeId = :challenge_id")
    Object findMemberNumOfChallenge(@Param("challenge_id") Long challengeId);

    Optional<MemberChallenge> findMemberChallengeByMemberAndChallenge(Member member, Challenge challenge);
}
