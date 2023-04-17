package com.lj.application.post.create.creditcard;

import com.lj.application.UseCase;
import com.lj.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateCreditCardPostUseCase extends UseCase<CreateCreditCardPostCommand, Either<Notification, CreateCreditCardPostOutput>> {
}
