package com.lj.application.creditcard.create;

import com.lj.application.UseCase;
import com.lj.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateCreditCardUseCase extends UseCase<CreateCreditCardCommand, Either<Notification, CreateCreditCardOutput>> {

}
