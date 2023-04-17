package com.lj.infrastructure.post.category.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreatePostCategoryApiInput(
        @JsonProperty("name") String name
) {
}
