package com.lj.application.post.subcategory.create;

import com.lj.application.UseCase;
import com.lj.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreatePostSubcategoryUseCase extends UseCase<CreatePostSubcategoryCommand, Either<Notification, CreatePostSubcategoryOutput>> {

}
