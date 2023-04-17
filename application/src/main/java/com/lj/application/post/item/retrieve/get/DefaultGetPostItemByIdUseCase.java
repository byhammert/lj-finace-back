package com.lj.application.post.item.retrieve.get;

import com.lj.domain.exceptions.NotFoundException;
import com.lj.domain.post.item.PostItem;
import com.lj.domain.post.item.PostItemGateway;
import com.lj.domain.post.item.PostItemID;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetPostItemByIdUseCase extends GetPostItemByIdUseCase {

    private final PostItemGateway postItemGateway;

    public DefaultGetPostItemByIdUseCase(final PostItemGateway postItemGateway) {
        this.postItemGateway = Objects.requireNonNull(postItemGateway);
    }

    @Override
    public PostItemOutput execute(String anId) {
        final var aPostItemId = PostItemID.from(anId);
        return postItemGateway.findById(aPostItemId)
                .map(PostItemOutput::from)
                .orElseThrow(notFound(aPostItemId));
    }

    private Supplier<NotFoundException> notFound(final PostItemID anId) {
        return () -> NotFoundException.with(PostItem.class, anId);
    }
}
