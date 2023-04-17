package com.lj.infrastructure.api.controllers;

import com.lj.application.post.subcategory.create.CreatePostSubcategoryCommand;
import com.lj.application.post.subcategory.create.CreatePostSubcategoryOutput;
import com.lj.application.post.subcategory.create.CreatePostSubcategoryUseCase;
import com.lj.application.post.subcategory.retrive.list.ListPostSubcategoriesUseCase;
import com.lj.domain.validation.handler.Notification;
import com.lj.infrastructure.api.PostSubcategoryAPI;
import com.lj.infrastructure.post.subcategory.PostSubcategoriesListApiPresenter;
import com.lj.infrastructure.post.subcategory.models.CreatePostSubcategoryApiInput;
import com.lj.infrastructure.post.subcategory.models.PostSubcategoriesListApiOutput;
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
public class PostSubcategoryController implements PostSubcategoryAPI {

    private final CreatePostSubcategoryUseCase createPostSubcategoryUseCase;
    private final ListPostSubcategoriesUseCase listPostSubcategoriesUseCase;

    public PostSubcategoryController(final CreatePostSubcategoryUseCase createPostSubcategoryUseCase,
                                     final ListPostSubcategoriesUseCase listPostSubcategoriesUseCase) {
        this.createPostSubcategoryUseCase = Objects.requireNonNull(createPostSubcategoryUseCase);
        this.listPostSubcategoriesUseCase = Objects.requireNonNull(listPostSubcategoriesUseCase);
    }

    @Override
    public ResponseEntity<?> createPostSubcategory(Principal principal, CreatePostSubcategoryApiInput input) {
        final var aCommand = CreatePostSubcategoryCommand.with(
                input.name(),
                input.postCategory()
        );
        final Function<Notification, ResponseEntity<?>> onError =
                ResponseEntity.unprocessableEntity()::body;

        final Function<CreatePostSubcategoryOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/posts/categories/" + output.id())).body(output);


        return this.createPostSubcategoryUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    public List<PostSubcategoriesListApiOutput> listPostCategories(final String aCategory) {
        return this.listPostSubcategoriesUseCase.execute(aCategory).stream()
                .map(PostSubcategoriesListApiPresenter::present)
                .toList();
    }

}
