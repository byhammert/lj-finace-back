package com.lj.infrastructure.post.subcategory;

import com.lj.application.post.category.retrieve.get.PostCategoryOutput;
import com.lj.application.post.subcategory.retrive.get.PostSubcategoryOutput;
import com.lj.infrastructure.post.subcategory.models.PostSubcategoryApiOutput;

import java.util.function.Function;

public interface PostCategoryApiPresenter {

    Function<PostSubcategoryOutput, PostSubcategoryApiOutput> present =
            output -> new PostSubcategoryApiOutput(
                    output.id().getValue(),
                    output.name()
            );

    static PostSubcategoryApiOutput present(final PostSubcategoryOutput output) {
        return new PostSubcategoryApiOutput(
          output.id().getValue(),
                output.name()
        );
    }
}
