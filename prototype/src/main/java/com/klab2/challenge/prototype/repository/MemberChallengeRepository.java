package com.klab2.challenge.prototype.repository;

import com.klab2.challenge.prototype.domain.MemberChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberChallengeRepository extends JpaRepository<MemberChallenge, Long> {
}
