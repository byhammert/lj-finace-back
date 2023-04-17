package com.lj.infrastructure.post.category;

import com.lj.application.post.category.retrieve.list.PostCategoriesListOutput;
import com.lj.infrastructure.post.category.models.PostCategoriesListApiOutput;

public interface PostCategoriesListApiPresenter {

    static PostCategoriesListApiOutput present(final PostCategoriesListOutput output) {
        return new PostCategoriesListApiOutput(
          output.id().getValue(),
                output.name()
        );
    }
}
