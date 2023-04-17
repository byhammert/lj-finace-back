package com.lj.infrastructure.configuration.usecases;

import com.lj.application.post.item.create.CreateListPostItemUseCase;
import com.lj.application.post.item.create.DefaultCreateListPostItemUseCase;
import com.lj.application.post.item.retrieve.get.DefaultGetPostItemByIdUseCase;
import com.lj.application.post.item.retrieve.get.GetPostItemByIdUseCase;
import com.lj.application.post.item.retrieve.list.DefaultListPostItemsUseCase;
import com.lj.application.post.item.retrieve.list.ListPostItemsUseCase;
import com.lj.domain.post.item.PostItemGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostItemUseCaseConfig {

    private final PostItemGateway gateway;

    public PostItemUseCaseConfig(final PostItemGateway gateway) {
        this.gateway = gateway;
    }

    @Bean
    public CreateListPostItemUseCase createPostItemUseCase() {
        return new DefaultCreateListPostItemUseCase(gateway);
    }

    @Bean
    public ListPostItemsUseCase listPostItemsUseCase() {
        return new DefaultListPostItemsUseCase(gateway);
    }

    @Bean
    public GetPostItemByIdUseCase getPostItemByIdUseCase() {
        return new DefaultGetPostItemByIdUseCase(gateway);
    }
}
