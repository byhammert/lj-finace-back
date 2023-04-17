package com.lj.application.post.category.create;

import com.lj.domain.post.category.PostCategory;
import com.lj.domain.post.category.PostCategoryGateway;
import com.lj.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.Try;

public class DefaultCreatePostCategoryUseCase extends CreatePostCategoryUseCase {

    private final PostCategoryGateway postCategoryGateway;

    public DefaultCreatePostCategoryUseCase(final PostCategoryGateway postCategoryGateway) {
        this.postCategoryGateway = Objects.requireNonNull(postCategoryGateway);
    }

    @Override
    public Either<Notification, CreatePostCategoryOutput> execute(final CreatePostCategoryCommand aCommand) {
        final var aName = aCommand.name();
        final var anUser = aCommand.user();

        final var notification = Notification.create();
        final var aPostCategory = PostCategory.newPostCategory(aName, anUser);
        aPostCategory.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(aPostCategory);
    }

    private Either<Notification, CreatePostCategoryOutput> create(final PostCategory aPostCategory) {
        return Try(() -> this.postCategoryGateway.create(aPostCategory))
                .toEither()
                .bimap(Notification::create, CreatePostCategoryOutput::from);
    }
}
