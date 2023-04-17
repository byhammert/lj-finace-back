package com.lj.domain.post.item;

import java.util.List;
import java.util.Optional;

public interface PostItemGateway {
    List<PostItem> create(List<PostItem> aPostItem);
    void deleteById(PostItemID anId);
    Optional<PostItem> findById(PostItemID anId);
    PostItem update(PostItem aPostItem);
    List<PostItem> findAllByPostOrderByNumberAsc(String post);
}
