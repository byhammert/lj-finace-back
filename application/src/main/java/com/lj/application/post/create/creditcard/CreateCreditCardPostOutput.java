package com.lj.application.post.create.creditcard;

import com.lj.domain.post.Post;

public record CreateCreditCardPostOutput(
        String id
) {
    public static CreateCreditCardPostOutput from(final String anId) {
        return new CreateCreditCardPostOutput(anId);
    }

    public static CreateCreditCardPostOutput from(final Post aPost) {
        return new CreateCreditCardPostOutput(aPost.getId().getValue());
    }
}
