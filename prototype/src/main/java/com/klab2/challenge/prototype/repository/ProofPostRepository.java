package com.klab2.challenge.prototype.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.klab2.challenge.prototype.domain.ProofPost;

public interface ProofPostRepository extends JpaRepository<ProofPost, Long> {

//    Optional<ProofPost> findByProofPostId(Long proofPostId);
//
//    @Query("SELECT p FROM ProofPost p " +
//            "WHERE p.challenge.challengeId = :challengeId")
//    List<ProofPost> getProofPostsByChallengeId(@Param("challengeId") Long challengeId, Pageable pageable);
}
