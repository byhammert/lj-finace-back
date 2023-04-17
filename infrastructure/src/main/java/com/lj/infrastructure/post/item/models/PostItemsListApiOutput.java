package com.lj.infrastructure.post.item.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PostItemsListApiOutput(
        @JsonProperty("id") String id,
        @JsonProperty("post") String post,
        @JsonProperty("number") Integer number,
        @JsonProperty("amount") Long amount,
        @JsonProperty("effective") Boolean effective,
        @JsonProperty("payment_date") LocalDateTime paymentDate,
        @JsonProperty("due_date") LocalDate dueDate
) {
}
