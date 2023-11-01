package com.klab2.challenge.prototype.repository;

import com.klab2.challenge.prototype.domain.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

}
