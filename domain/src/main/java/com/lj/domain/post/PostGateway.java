package com.lj.domain.post;

import com.lj.domain.PaginationSearchQuery;
import com.lj.domain.pagination.Pagination;

import java.util.Optional;

public interface PostGateway {
    Post create(Post aPost);
    void deleteById(PostID anId);
    Optional<Post> findById(PostID anId);
    Post update(Post aPost);
    Pagination<Post> findAll(PaginationSearchQuery aQuery);
}
