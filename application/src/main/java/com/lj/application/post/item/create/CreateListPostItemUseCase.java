package com.lj.application.post.item.create;

import com.lj.application.UseCase;
import com.lj.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateListPostItemUseCase extends UseCase<CreateListPostItemCommand, Either<Notification, CreateListPostItemOutput>> {

}
