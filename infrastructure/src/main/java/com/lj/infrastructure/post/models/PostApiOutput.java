package com.lj.infrastructure.post.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lj.application.account.retrieve.get.AccountOutput;
import com.lj.infrastructure.account.models.AccountApiOutput;
import com.lj.infrastructure.post.category.models.PostCategoryApiOutput;
import com.lj.infrastructure.post.item.models.PostItemApiOutput;
import com.lj.infrastructure.post.subcategory.models.PostSubcategoryApiOutput;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record PostApiOutput(
        @JsonProperty("description") String description,
        @JsonProperty("category") PostCategoryApiOutput category,
        @JsonProperty("subcategory") PostSubcategoryApiOutput subcategory,
        @JsonProperty("recurrence") String recurrence,
        @JsonProperty("account") AccountApiOutput account,
        @JsonProperty("note") String note,
        @JsonProperty("effective") Boolean effective,
        @JsonProperty("amount") Long amount,
        @JsonProperty("post_type") String postType,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("updated_at") Instant updatedAt,
        @JsonProperty("post_items") List<PostItemApiOutput> postItems
) {
}
