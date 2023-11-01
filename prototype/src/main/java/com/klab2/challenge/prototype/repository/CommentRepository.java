package com.klab2.challenge.prototype.repository;


import com.klab2.challenge.prototype.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
//    Optional<Comment> findByName(String Name);
//
//    @Query("select c from Comment c where c.pp_id =:pp_id")
//    List<Comment> getAllCommentByPP_id(@Param("pp_id") Long pp_id);

}
