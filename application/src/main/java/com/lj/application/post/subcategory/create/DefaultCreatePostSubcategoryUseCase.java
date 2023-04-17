package com.lj.application.post.subcategory.create;

import com.lj.domain.post.category.PostCategory;
import com.lj.domain.post.subcategory.PostSubcategory;
import com.lj.domain.post.subcategory.PostSubcategoryGateway;
import com.lj.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.Try;

public class DefaultCreatePostSubcategoryUseCase extends CreatePostSubcategoryUseCase {

    private final PostSubcategoryGateway postSubcategoryGateway;

    public DefaultCreatePostSubcategoryUseCase(final PostSubcategoryGateway postSubcategoryGateway) {
        this.postSubcategoryGateway = Objects.requireNonNull(postSubcategoryGateway);
    }

    @Override
    public Either<Notification, CreatePostSubcategoryOutput> execute(final CreatePostSubcategoryCommand aCommand) {
        final var aName = aCommand.name();
        final var aPostCategory = aCommand.postCategory();

        final var notification = Notification.create();
        final var aPostSubcategory = PostSubcategory.newPostSubcategory(aName, aPostCategory);
        aPostSubcategory.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(aPostSubcategory);
    }

    private Either<Notification, CreatePostSubcategoryOutput> create(final PostSubcategory aPostSubcategory) {
        return Try(() -> this.postSubcategoryGateway.create(aPostSubcategory))
                .toEither()
                .bimap(Notification::create, CreatePostSubcategoryOutput::from);
    }
}
