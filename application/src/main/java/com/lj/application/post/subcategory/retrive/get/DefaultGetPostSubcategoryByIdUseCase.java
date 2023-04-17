package com.lj.application.post.subcategory.retrive.get;

import com.lj.domain.exceptions.NotFoundException;
import com.lj.domain.post.subcategory.PostSubcategory;
import com.lj.domain.post.subcategory.PostSubcategoryGateway;
import com.lj.domain.post.subcategory.PostSubcategoryID;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetPostSubcategoryByIdUseCase extends GetPostSubcategoryByIdUseCase {

    private final PostSubcategoryGateway postSubcategoryGateway;

    public DefaultGetPostSubcategoryByIdUseCase(final PostSubcategoryGateway postSubcategoryGateway) {
        this.postSubcategoryGateway = Objects.requireNonNull(postSubcategoryGateway);
    }

    @Override
    public PostSubcategoryOutput execute(String anId) {
        final var anPostSubcategoryId = PostSubcategoryID.from(anId);
        return postSubcategoryGateway.findById(anPostSubcategoryId)
                .map(PostSubcategoryOutput::from)
                .orElseThrow(notFound(anPostSubcategoryId));
    }

    private Supplier<NotFoundException> notFound(final PostSubcategoryID anId) {
        return () -> NotFoundException.with(PostSubcategory.class, anId);
    }
}
