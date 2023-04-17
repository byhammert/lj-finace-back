package com.lj.infrastructure.post.category;

import com.lj.application.post.category.retrieve.get.PostCategoryOutput;
import com.lj.infrastructure.post.category.models.PostCategoryApiOutput;

import java.util.function.Function;

public interface PostCategoryApiPresenter {

    Function<PostCategoryOutput, PostCategoryApiOutput> present =
            output -> new PostCategoryApiOutput(
                    output.id().getValue(),
                    output.name()
            );

    static PostCategoryApiOutput present(final PostCategoryOutput output) {
        return new PostCategoryApiOutput(
          output.id().getValue(),
                output.name()
        );
    }
}
