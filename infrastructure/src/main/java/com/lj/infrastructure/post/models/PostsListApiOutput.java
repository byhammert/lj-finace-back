package com.lj.infrastructure.post.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lj.domain.post.PostType;
import com.lj.infrastructure.account.models.AccountApiOutput;
import com.lj.infrastructure.post.category.models.PostCategoryApiOutput;
import com.lj.infrastructure.post.item.models.PostItemApiOutput;
import com.lj.infrastructure.post.subcategory.models.PostSubcategoryApiOutput;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record PostsListApiOutput(
        @JsonProperty("id") String id,
        @JsonProperty("description") String description,
        @JsonProperty("effective") Boolean effective,
        @JsonProperty("amount") Long amount,
        @JsonProperty("post_type") PostType postType,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("updated_at") Instant updatedAt
) {
}
