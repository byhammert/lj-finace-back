package com.lj.infrastructure.configuration.usecases;

import com.lj.application.account.retrieve.get.GetAccountByIdUseCase;
import com.lj.application.account.update.UpdateAccountUseCase;
import com.lj.application.post.category.create.CreatePostCategoryUseCase;
import com.lj.application.post.category.create.DefaultCreatePostCategoryUseCase;
import com.lj.application.post.category.retrieve.get.DefaultGetPostCategoryByIdUseCase;
import com.lj.application.post.category.retrieve.get.GetPostCategoryByIdUseCase;
import com.lj.application.post.category.retrieve.list.DefaultListPostCategoriesUseCase;
import com.lj.application.post.category.retrieve.list.ListPostCategoriesUseCase;
import com.lj.application.post.create.revenue.CreateRevenuePostUseCase;
import com.lj.application.post.create.revenue.DefaultCreateRevenuePostUseCase;
import com.lj.application.post.item.create.CreateListPostItemUseCase;
import com.lj.application.post.subcategory.retrive.get.GetPostSubcategoryByIdUseCase;
import com.lj.domain.post.PostGateway;
import com.lj.domain.post.category.PostCategoryGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostCategoryUseCaseConfig {

    private final PostCategoryGateway gateway;

    public PostCategoryUseCaseConfig(final PostCategoryGateway gateway) {
        this.gateway = gateway;
    }

    @Bean
    public CreatePostCategoryUseCase createPostCategoryUseCase() {
        return new DefaultCreatePostCategoryUseCase(gateway);
    }

    @Bean
    public ListPostCategoriesUseCase listPostCategoriesUseCase() {
        return new DefaultListPostCategoriesUseCase(gateway);
    }

    @Bean
    public GetPostCategoryByIdUseCase getPostCategoryByIdUseCase() {
        return new DefaultGetPostCategoryByIdUseCase(gateway);
    }
}
