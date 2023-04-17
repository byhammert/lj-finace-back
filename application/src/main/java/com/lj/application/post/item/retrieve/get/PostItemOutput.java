package com.lj.application.post.item.retrieve.get;

import com.lj.domain.post.item.PostItem;
import com.lj.domain.post.item.PostItemID;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PostItemOutput(
        PostItemID id,
        String post,
        int number,
        long amount,
        boolean effective,
        LocalDateTime paymentDate,
        LocalDate dueDate

) {
    public static PostItemOutput from(final PostItem aPostItem) {
        return new PostItemOutput(
                aPostItem.getId(),
                aPostItem.getPost(),
                aPostItem.getNumber(),
                aPostItem.getAmount(),
                aPostItem.isEffective(),
                aPostItem.getPaymentDate(),
                aPostItem.getDueDate()
        );
    }
}
