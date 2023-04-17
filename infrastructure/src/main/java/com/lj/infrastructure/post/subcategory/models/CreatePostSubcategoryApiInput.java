package com.lj.infrastructure.post.subcategory.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreatePostSubcategoryApiInput(
        @JsonProperty("name") String name,
        @JsonProperty("category") String postCategory
) {
}
