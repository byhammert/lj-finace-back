package com.lj.infrastructure.post.subcategory.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PostSubcategoriesListApiOutput(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name
) {
}
