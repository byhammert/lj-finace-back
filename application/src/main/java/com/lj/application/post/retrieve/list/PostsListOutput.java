package com.lj.application.post.retrieve.list;

import com.lj.domain.post.Post;
import com.lj.domain.post.PostID;
import com.lj.domain.post.PostType;
import com.lj.domain.post.RecurrenceType;
import com.lj.domain.post.category.PostCategory;
import com.lj.domain.post.category.PostCategoryID;

import java.time.Instant;

public record PostsListOutput(
        PostID id,
        String description,
        String category,
        String subcategory,
        RecurrenceType recurrence,
        String account,
        String note,
        PostType postType,
        boolean effective,
        long amount,
        Instant createdAt,
        Instant updatedAt
) {
    public static PostsListOutput from(final Post aPost) {
        return new PostsListOutput(
                aPost.getId(),
                aPost.getDescription(),
                aPost.getCategory(),
                aPost.getSubcategory(),
                aPost.getRecurrence(),
                aPost.getAccount(),
                aPost.getNote(),
                aPost.getPostType(),
                aPost.isEffective(),
                aPost.getAmount(),
                aPost.getCreatedAt(),
                aPost.getUpdatedAt()
        );
    }
}
