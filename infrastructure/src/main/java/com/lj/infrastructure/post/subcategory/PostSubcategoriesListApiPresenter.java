package com.lj.infrastructure.post.subcategory;

import com.lj.application.post.subcategory.retrive.list.PostSubcategoriesListOutput;
import com.lj.infrastructure.post.subcategory.models.PostSubcategoriesListApiOutput;

public interface PostSubcategoriesListApiPresenter {

    static PostSubcategoriesListApiOutput present(final PostSubcategoriesListOutput output) {
        return new PostSubcategoriesListApiOutput(
          output.id().getValue(),
                output.name()
        );
    }
}
