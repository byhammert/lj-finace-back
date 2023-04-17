package com.lj.application.post.category.create;

import com.lj.domain.account.Account;
import com.lj.domain.post.category.PostCategory;

public record CreatePostCategoryOutput(
        String id
) {
    public static CreatePostCategoryOutput from(final String anId) {
        return new CreatePostCategoryOutput(anId);
    }

    public static CreatePostCategoryOutput from(final PostCategory aPostCategory) {
        return new CreatePostCategoryOutput(aPostCategory.getId().getValue());
    }
}
