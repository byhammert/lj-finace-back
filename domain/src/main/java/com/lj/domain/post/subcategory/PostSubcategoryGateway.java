package com.lj.domain.post.subcategory;

import com.lj.domain.post.category.PostCategory;
import com.lj.domain.post.category.PostCategoryID;

import java.util.List;
import java.util.Optional;

public interface PostSubcategoryGateway {
    PostSubcategory create(PostSubcategory aPostSubcategory);
    void deleteById(PostSubcategoryID anId);
    Optional<PostSubcategory> findById(PostSubcategoryID anId);
    PostSubcategory update(PostSubcategory aPostCategory);
    List<PostSubcategory> findAllByPostCategoryOrderByNameAsc(String postCategory);
}
