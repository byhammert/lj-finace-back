package com.lj.application.post.item.retrieve.list;

import com.lj.domain.post.item.PostItemGateway;

import java.util.List;
import java.util.Objects;

public class DefaultListPostItemsUseCase extends ListPostItemsUseCase {

    private final PostItemGateway postItemGateway;

    public DefaultListPostItemsUseCase(final PostItemGateway postItemGateway) {
        this.postItemGateway = Objects.requireNonNull(postItemGateway);
    }

    @Override
    public List<PostItemsListOutput> execute(String anId) {
        return this.postItemGateway.findAllByPostOrderByNumberAsc(anId).stream()
                .map(PostItemsListOutput::from)
                .toList();
    }
}
