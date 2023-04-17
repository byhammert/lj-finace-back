package com.lj.infrastructure.post.category.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PostCategoriesListApiOutput(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name
) {
}
