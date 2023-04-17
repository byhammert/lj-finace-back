package com.lj.application.post.retrieve.list;

import com.lj.application.UseCase;
import com.lj.domain.PaginationSearchQuery;
import com.lj.domain.pagination.Pagination;

public abstract class ListPostsUseCase extends UseCase<PaginationSearchQuery, Pagination<PostsListOutput>> {
}
