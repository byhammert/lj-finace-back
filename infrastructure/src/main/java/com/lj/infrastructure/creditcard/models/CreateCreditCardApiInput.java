package com.lj.infrastructure.creditcard.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateCreditCardApiInput(
        @JsonProperty("description") String description,
        @JsonProperty("account") String account,
        @JsonProperty("closing_day") Integer closingDay,
        @JsonProperty("due_day") Integer dueDay,
        @JsonProperty("limit") Long limit
) {
}
