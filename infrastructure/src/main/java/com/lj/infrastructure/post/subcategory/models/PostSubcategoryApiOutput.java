package com.lj.infrastructure.post.subcategory.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PostSubcategoryApiOutput(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name

) {
}
