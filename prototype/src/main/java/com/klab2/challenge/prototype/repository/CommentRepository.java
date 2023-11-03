package com.klab2.challenge.prototype.repository;


import com.klab2.challenge.prototype.domain.Comment;
import com.klab2.challenge.prototype.domain.ProofPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query("SELECT c FROM Comment c " +
            "WHERE c.proofPost.proofPostId = :proofPostId")
    List<Comment> findAllCommentsByProofPost(@Param("proofPostId") Long proofPostId);
}
