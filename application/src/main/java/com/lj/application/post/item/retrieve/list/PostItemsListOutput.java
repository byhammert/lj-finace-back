package com.lj.application.post.item.retrieve.list;

import com.lj.domain.post.category.PostCategory;
import com.lj.domain.post.category.PostCategoryID;
import com.lj.domain.post.item.PostItem;
import com.lj.domain.post.item.PostItemID;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PostItemsListOutput(
        PostItemID id,
        String post,
        int number,
        long amount,
        boolean effective,
        LocalDateTime paymentDate,
        LocalDate dueDate
) {
    public static PostItemsListOutput from(final PostItem aPostItem) {
        return new PostItemsListOutput(
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
