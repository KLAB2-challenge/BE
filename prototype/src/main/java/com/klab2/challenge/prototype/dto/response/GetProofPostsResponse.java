package com.klab2.challenge.prototype.dto.response;

import com.klab2.challenge.prototype.domain.ProofPost;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

@Getter
@AllArgsConstructor
public class GetProofPostsResponse {
    private List<ProofPost> proofPosts;
}
