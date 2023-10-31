package com.klab2.challenge.prototype.repository;

import com.klab2.challenge.prototype.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.klab2.challenge.prototype.domain.ProofPost;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface ProofPostRepository extends JpaRepository<ProofPost, Long> {

    Optional<ProofPost> findByProofPostId(Long proofPostId);

    @Query("SELECT p FROM ProofPost p " +
            "WHERE p.challenge.challengeId = :challengeId")
    List<ProofPost> getProofPostsByChallengeId(@Param("challengeId") Long challengeId, Pageable pageable);
}
