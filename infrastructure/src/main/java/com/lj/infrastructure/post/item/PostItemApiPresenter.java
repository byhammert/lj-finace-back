package com.lj.infrastructure.post.item;

import com.lj.application.post.item.retrieve.get.PostItemOutput;
import com.lj.infrastructure.post.item.models.PostItemApiOutput;

import java.util.function.Function;

public interface PostItemApiPresenter {

    Function<PostItemOutput, PostItemApiOutput> present =
            output -> new PostItemApiOutput(
                    output.id().getValue(),
                    output.post(),
                    output.number(),
                    output.amount(),
                    output.effective(),
                    output.paymentDate(),
                    output.dueDate()
            );

    static PostItemApiOutput present(final PostItemOutput output) {
        return new PostItemApiOutput(
          output.id().getValue(),
                output.post(),
                output.number(),
                output.amount(),
                output.effective(),
                output.paymentDate(),
                output.dueDate()
        );
    }
}
