package com.lj.application.post.subcategory.retrive.list;

import com.lj.domain.post.category.PostCategory;
import com.lj.domain.post.category.PostCategoryID;
import com.lj.domain.post.subcategory.PostSubcategory;
import com.lj.domain.post.subcategory.PostSubcategoryID;

public record PostSubcategoriesListOutput(
        PostSubcategoryID id,
        String name
) {
    public static PostSubcategoriesListOutput from(final PostSubcategory aPostSubcategory) {
        return new PostSubcategoriesListOutput(
                aPostSubcategory.getId(),
                aPostSubcategory.getName()
        );
    }
}
