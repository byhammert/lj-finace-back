package com.lj.application.post.category.create;

import com.lj.application.UseCase;
import com.lj.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreatePostCategoryUseCase extends UseCase<CreatePostCategoryCommand, Either<Notification, CreatePostCategoryOutput>> {

}
