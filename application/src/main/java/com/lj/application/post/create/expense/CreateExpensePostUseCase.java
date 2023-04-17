package com.lj.application.post.create.expense;

import com.lj.application.UseCase;
import com.lj.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateExpensePostUseCase extends UseCase<CreateExpensePostCommand, Either<Notification, CreateExpensePostOutput>> {
}
