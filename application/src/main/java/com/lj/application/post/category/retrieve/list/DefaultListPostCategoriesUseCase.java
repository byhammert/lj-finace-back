package com.lj.application.post.category.retrieve.list;

import com.lj.domain.account.AccountGateway;
import com.lj.domain.post.category.PostCategoryGateway;

import java.util.List;
import java.util.Objects;

public class DefaultListPostCategoriesUseCase extends ListPostCategoriesUseCase {

    private final PostCategoryGateway postCategoryGateway;

    public DefaultListPostCategoriesUseCase(PostCategoryGateway postCategoryGateway) {
        this.postCategoryGateway = Objects.requireNonNull(postCategoryGateway);
    }

    @Override
    public List<PostCategoriesListOutput> execute(String anId) {
        return this.postCategoryGateway.findAllByUserOrderByNameAsc(anId).stream()
                .map(PostCategoriesListOutput::from)
                .toList();
    }
}
