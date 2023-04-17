package com.lj.infrastructure.post.subcategory.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdatePostSubcategoryApiInput(
        @JsonProperty("name") String name
) {
}
