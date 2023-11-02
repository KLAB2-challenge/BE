package com.klab2.challenge.prototype.repository;

import com.klab2.challenge.prototype.domain.Challenge;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    @Query(value = "SELECT c FROM Challenge c " +
            "JOIN MemberChallenge mc " +
            "ON c.challengeId = mc.challenge.challengeId " +
            "GROUP BY c.challengeId " +
            "ORDER BY COUNT(*) DESC",
            countQuery = "SELECT COUNT(c) FROM Challenge c")
    List<Challenge> getPopularChallenges(Pageable pageable);
}
