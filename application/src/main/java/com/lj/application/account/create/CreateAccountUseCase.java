package com.lj.application.account.create;

import com.lj.application.UseCase;
import com.lj.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateAccountUseCase extends UseCase<CreateAccountCommand, Either<Notification, CreateAccountOutput>> {

}
