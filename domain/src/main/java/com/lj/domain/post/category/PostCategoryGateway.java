package com.lj.domain.post.category;

import com.lj.domain.PaginationSearchQuery;
import com.lj.domain.pagination.Pagination;
import com.lj.domain.post.Post;
import com.lj.domain.post.PostID;

import java.util.List;
import java.util.Optional;

public interface PostCategoryGateway {
    PostCategory create(PostCategory aPostCategory);
    void deleteById(PostCategoryID anId);
    Optional<PostCategory> findById(PostCategoryID anId);
    PostCategory update(PostCategory aPostCategory);
    List<PostCategory> findAllByUserOrderByNameAsc(String user);
}
