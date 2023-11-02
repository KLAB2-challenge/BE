package com.klab2.challenge.prototype.repository;

import com.klab2.challenge.prototype.domain.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import com.klab2.challenge.prototype.domain.ProofPost;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProofPostRepository extends JpaRepository<ProofPost, Long> {


    @Query("SELECT p FROM ProofPost p " +
            "WHERE p.challenge.challengeId = :challengeId")
    Optional<ProofPost> findByChallenge(@Param("challengeId") Long challengeId);
//
//
//
//    List<ProofPost> getProofPostsByChallengeId(@Param("challengeId") Long challengeId, Pageable pageable);
}
