package com.klab2.challenge.prototype.repository;

import com.klab2.challenge.prototype.domain.Border;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BorderRepository extends JpaRepository<Border, Long> {

}
