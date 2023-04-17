package com.lj.application.post.subcategory.retrive.get;

import com.lj.domain.post.category.PostCategory;
import com.lj.domain.post.category.PostCategoryID;
import com.lj.domain.post.subcategory.PostSubcategory;
import com.lj.domain.post.subcategory.PostSubcategoryID;

public record PostSubcategoryOutput(
        PostSubcategoryID id,
        String name
) {
    public static PostSubcategoryOutput from(final PostSubcategory aPostSubcategory) {
        return new PostSubcategoryOutput(
                aPostSubcategory.getId(),
                aPostSubcategory.getName()
        );
    }
}
