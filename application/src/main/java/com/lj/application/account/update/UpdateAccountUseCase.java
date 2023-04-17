package com.lj.application.account.update;

import com.lj.application.UseCase;
import com.lj.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateAccountUseCase extends UseCase<UpdateAccountCommand, Either<Notification, UpdateAccountOutput>> {
}
