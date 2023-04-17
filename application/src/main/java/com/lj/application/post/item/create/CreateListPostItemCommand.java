package com.lj.application.post.item.create;

import java.time.LocalDate;

public record CreateListPostItemCommand(
        String post,
        int installment,
        long amount,
        LocalDate dueDate
) {

    public static CreateListPostItemCommand with(final String post,
                                                 final int installment,
                                                 final long amount,
                                                 LocalDate dueDate) {
        return new CreateListPostItemCommand(post,
                installment,
                amount,
                dueDate);
    }

}
