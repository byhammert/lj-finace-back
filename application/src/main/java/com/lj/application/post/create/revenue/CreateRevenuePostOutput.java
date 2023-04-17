package com.lj.application.post.create.revenue;

import com.lj.domain.account.Account;
import com.lj.domain.post.Post;

public record CreateRevenuePostOutput(
        String id
) {
    public static CreateRevenuePostOutput from(final String anId) {
        return new CreateRevenuePostOutput(anId);
    }

    public static CreateRevenuePostOutput from(final Post aPost) {
        return new CreateRevenuePostOutput(aPost.getId().getValue());
    }
}
