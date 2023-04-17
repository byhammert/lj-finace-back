package com.lj.application.post.create.expense;

import com.lj.domain.post.Post;

public record CreateExpensePostOutput(
        String id
) {
    public static CreateExpensePostOutput from(final String anId) {
        return new CreateExpensePostOutput(anId);
    }

    public static CreateExpensePostOutput from(final Post aPost) {
        return new CreateExpensePostOutput(aPost.getId().getValue());
    }
}
