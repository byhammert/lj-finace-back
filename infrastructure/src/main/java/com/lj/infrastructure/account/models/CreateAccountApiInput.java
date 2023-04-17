package com.lj.infrastructure.account.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateAccountApiInput(
        @JsonProperty("name") String name,
        @JsonProperty("category") String category,
        @JsonProperty("symbol") String symbol,
        @JsonProperty("balance") Long balance,
        @JsonProperty("is_active") Boolean isActive
) {
}
