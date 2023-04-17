package com.lj.application.post.retrieve.list;

import com.lj.domain.PaginationSearchQuery;
import com.lj.domain.pagination.Pagination;
import com.lj.domain.post.PostGateway;

import java.util.Objects;

public class DefaultListPostsUseCase extends ListPostsUseCase {

    private final PostGateway postGateway;

    public DefaultListPostsUseCase(final PostGateway postGateway) {
        this.postGateway = Objects.requireNonNull(postGateway);
    }

    @Override
    public Pagination<PostsListOutput> execute(final PaginationSearchQuery aQuery) {
        return this.postGateway.findAll(aQuery)
                .map(PostsListOutput::from);
    }
}
