package com.klab2.challenge.prototype.repository;

import com.klab2.challenge.prototype.domain.Challenge;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.klab2.challenge.prototype.domain.ProofPost;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProofPostRepository extends JpaRepository<ProofPost, Long> {

    @Query(value = "SELECT p FROM ProofPost p " +
            "WHERE p.challenge.challengeId = :challengeId",
            countQuery = "SELECT COUNT(p) FROM ProofPost p " +
                    "WHERE p.challenge.challengeId = :challengeId")
    List<ProofPost> findSomeProofPostsByChallengeId(@Param("challengeId") Long challengeId, Pageable pageable);

    @Query("SELECT p FROM ProofPost p " +
            "WHERE p.challenge.challengeId = :challengeId")
    List<ProofPost> findAllProofPostsByChallengeId(@Param("challengeId") Long challengeId);

    Optional<ProofPost> findProofPostByProofPostId(Long proofPostId);


}
