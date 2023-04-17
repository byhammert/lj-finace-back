package com.lj.application.post.category.retrieve.get;

import com.lj.domain.account.Account;
import com.lj.domain.account.AccountGateway;
import com.lj.domain.account.AccountID;
import com.lj.domain.exceptions.NotFoundException;
import com.lj.domain.post.category.PostCategory;
import com.lj.domain.post.category.PostCategoryGateway;
import com.lj.domain.post.category.PostCategoryID;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetPostCategoryByIdUseCase extends GetPostCategoryByIdUseCase {

    private final PostCategoryGateway postCategoryGateway;

    public DefaultGetPostCategoryByIdUseCase(PostCategoryGateway postCategoryGateway) {
        this.postCategoryGateway = Objects.requireNonNull(postCategoryGateway);
    }

    @Override
    public PostCategoryOutput execute(String anId) {
        final var anPostCategoryId = PostCategoryID.from(anId);
        return postCategoryGateway.findById(anPostCategoryId)
                .map(PostCategoryOutput::from)
                .orElseThrow(notFound(anPostCategoryId));
    }

    private Supplier<NotFoundException> notFound(final PostCategoryID anId) {
        return () -> NotFoundException.with(PostCategory.class, anId);
    }
}
