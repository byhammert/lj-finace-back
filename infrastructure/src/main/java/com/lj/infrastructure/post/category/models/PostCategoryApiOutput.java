package com.lj.infrastructure.post.category.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record PostCategoryApiOutput(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name
) {
}
