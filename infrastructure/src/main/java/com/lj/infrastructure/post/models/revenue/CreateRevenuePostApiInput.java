package com.lj.infrastructure.post.models.revenue;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateRevenuePostApiInput(
        @JsonProperty("description") String description,
        @JsonProperty("category") String category,
        @JsonProperty("subcategory") String subcategory,
        @JsonProperty("recurrence") String recurrence,
        @JsonProperty("account") String account,
        @JsonProperty("note") String note,
        @JsonProperty("effective") Boolean effective,
        @JsonProperty("amount") Long amount,
        @JsonProperty("installment") Integer installment,
        @JsonProperty("due_date") LocalDate dueDate


) {
}
