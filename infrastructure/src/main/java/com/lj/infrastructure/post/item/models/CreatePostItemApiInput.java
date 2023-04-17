package com.lj.infrastructure.post.item.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record CreatePostItemApiInput(
        @JsonProperty("post") String post,
        @JsonProperty("number") String number,
        @JsonProperty("amount") String amount,
        @JsonProperty("effective") Boolean effective,
        @JsonProperty("payment_date") LocalDateTime paymentDate,
        @JsonProperty("due_date") LocalDateTime dueDate

) {
}
