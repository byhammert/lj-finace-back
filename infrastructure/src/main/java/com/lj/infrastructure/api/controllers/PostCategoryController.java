package com.lj.infrastructure.api.controllers;

import com.lj.application.post.category.create.CreatePostCategoryCommand;
import com.lj.application.post.category.create.CreatePostCategoryOutput;
import com.lj.application.post.category.create.CreatePostCategoryUseCase;
import com.lj.application.post.category.retrieve.list.ListPostCategoriesUseCase;
import com.lj.domain.validation.handler.Notification;
import com.lj.infrastructure.api.PostCategoryAPI;
import com.lj.infrastructure.post.category.PostCategoriesListApiPresenter;
import com.lj.infrastructure.post.category.models.CreatePostCategoryApiInput;
import com.lj.infrastructure.post.category.models.PostCategoriesListApiOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@RestController
@CrossOrigin(origins = "*")
public class PostCategoryController implements PostCategoryAPI {

    private final CreatePostCategoryUseCase createPostCategoryUseCase;
    private final ListPostCategoriesUseCase listPostCategoriesUseCase;

    public PostCategoryController(final CreatePostCategoryUseCase createPostCategoryUseCase,
                                  final ListPostCategoriesUseCase listPostCategoriesUseCase) {
        this.createPostCategoryUseCase = Objects.requireNonNull(createPostCategoryUseCase);
        this.listPostCategoriesUseCase = Objects.requireNonNull(listPostCategoriesUseCase);
    }

    @Override
    public ResponseEntity<?> createPostCategory(Principal principal, CreatePostCategoryApiInput input) {
        final var aCommand = CreatePostCategoryCommand.with(
                input.name(),
                principal.getName()
        );
        final Function<Notification, ResponseEntity<?>> onError =
                ResponseEntity.unprocessableEntity()::body;

        final Function<CreatePostCategoryOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/posts/categories/" + output.id())).body(output);


        return this.createPostCategoryUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    public List<PostCategoriesListApiOutput> listPostCategories(Principal principal) {
        return this.listPostCategoriesUseCase.execute(principal.getName()).stream()
                .map(PostCategoriesListApiPresenter::present)
                .toList();
    }
}
