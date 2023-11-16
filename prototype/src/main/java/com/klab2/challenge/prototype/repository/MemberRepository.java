package com.klab2.challenge.prototype.repository;

import com.klab2.challenge.prototype.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m FROM Member m " +
            "WHERE m.name = :memberName")
    Optional<Member> findByName(@Param("memberName") String name);

    @Modifying
    @Query("UPDATE Member m " +
            "SET m.infos.totalCoins = :totalCoins, " +
            "m.infos.holdingCoins = :holdingCoins " +
            "WHERE m.memberId = :memberId")
    void setMemberCoins(@Param("memberId") Long memberId,
                        @Param("totalCoins") int totalCoins,
                        @Param("holdingCoins") int holdingCoins);

    @Modifying
    @Query("UPDATE Member m " +
            "SET m.infos.currentBorder = :borderId " +
            "WHERE m.memberId = :memberId")
    void changeCurrentBorder(@Param("memberId") Long memberId,
                             @Param("borderId") Long borderId);

    @Query("SELECT count(*) FROM Member m where m.infos.holdingCoins > :totalCoins")
    Optional<Integer> findMyRankByName(@Param("totalCoins") int totalCoins);

}
