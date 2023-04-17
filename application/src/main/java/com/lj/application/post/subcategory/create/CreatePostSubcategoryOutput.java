package com.lj.application.post.subcategory.create;

import com.lj.domain.post.subcategory.PostSubcategory;

public record CreatePostSubcategoryOutput(
        String id
) {
    public static CreatePostSubcategoryOutput from(final String anId) {
        return new CreatePostSubcategoryOutput(anId);
    }

    public static CreatePostSubcategoryOutput from(final PostSubcategory aPostSubcategory) {
        return new CreatePostSubcategoryOutput(aPostSubcategory.getId().getValue());
    }
}
