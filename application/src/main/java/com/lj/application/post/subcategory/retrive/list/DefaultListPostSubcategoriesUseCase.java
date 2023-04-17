package com.lj.application.post.subcategory.retrive.list;

import com.lj.domain.post.category.PostCategoryGateway;
import com.lj.domain.post.subcategory.PostSubcategoryGateway;

import java.util.List;
import java.util.Objects;

public class DefaultListPostSubcategoriesUseCase extends ListPostSubcategoriesUseCase {

    private final PostSubcategoryGateway postSubcategoryGateway;

    public DefaultListPostSubcategoriesUseCase(PostSubcategoryGateway postSubcategoryGateway) {
        this.postSubcategoryGateway = Objects.requireNonNull(postSubcategoryGateway);
    }

    @Override
    public List<PostSubcategoriesListOutput> execute(String aCategory) {
        return this.postSubcategoryGateway.findAllByPostCategoryOrderByNameAsc(aCategory).stream()
                .map(PostSubcategoriesListOutput::from)
                .toList();
    }
}
