package com.lj.infrastructure.creditcard.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateCreditCardApiInput(
        @JsonProperty("name") String name,
        @JsonProperty("category") String category,
        @JsonProperty("symbol") String symbol,
        @JsonProperty("balance") Long balance,
        @JsonProperty("is_active") Boolean isActive
) {
}
