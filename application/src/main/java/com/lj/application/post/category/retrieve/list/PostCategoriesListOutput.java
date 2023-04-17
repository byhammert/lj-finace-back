package com.lj.application.post.category.retrieve.list;

import com.lj.domain.account.Account;
import com.lj.domain.account.AccountID;
import com.lj.domain.post.category.PostCategory;
import com.lj.domain.post.category.PostCategoryID;

import java.time.Instant;

public record PostCategoriesListOutput(
        PostCategoryID id,
        String name
) {
    public static PostCategoriesListOutput from(final PostCategory aPostCategory) {
        return new PostCategoriesListOutput(
                aPostCategory.getId(),
                aPostCategory.getName()
        );
    }
}
