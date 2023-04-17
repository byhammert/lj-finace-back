package com.lj.infrastructure.configuration.usecases;

import com.lj.application.post.subcategory.create.CreatePostSubcategoryUseCase;
import com.lj.application.post.subcategory.create.DefaultCreatePostSubcategoryUseCase;
import com.lj.application.post.subcategory.retrive.get.DefaultGetPostSubcategoryByIdUseCase;
import com.lj.application.post.subcategory.retrive.get.GetPostSubcategoryByIdUseCase;
import com.lj.application.post.subcategory.retrive.list.DefaultListPostSubcategoriesUseCase;
import com.lj.application.post.subcategory.retrive.list.ListPostSubcategoriesUseCase;
import com.lj.domain.post.subcategory.PostSubcategoryGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostSubcategoryUseCaseConfig {

    private final PostSubcategoryGateway gateway;

    public PostSubcategoryUseCaseConfig(final PostSubcategoryGateway gateway) {
        this.gateway = gateway;
    }

    @Bean
    public CreatePostSubcategoryUseCase createPostSubcategoryUseCase() {
        return new DefaultCreatePostSubcategoryUseCase(gateway);
    }

    @Bean
    public ListPostSubcategoriesUseCase listPostSubcategoriesUseCase() {
        return new DefaultListPostSubcategoriesUseCase(gateway);
    }

    @Bean
    public GetPostSubcategoryByIdUseCase getPostSubcategoryByIdUseCase() {
        return new DefaultGetPostSubcategoryByIdUseCase(gateway);
    }
}
