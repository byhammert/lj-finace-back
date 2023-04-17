package com.lj.infrastructure.account.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record AccountApiOutput(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("category") String category,
        @JsonProperty("symbol") String symbol,
        @JsonProperty("balance") Long balance,
        @JsonProperty("is_active") Boolean isActive,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("updated_at") Instant updatedAt,
        @JsonProperty("deleted_at") Instant deletedAt
) {
}
