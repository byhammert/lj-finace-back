package com.lj.application.account.update.addamount;

import com.lj.application.UseCase;
import com.lj.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateAccountAddAmountUseCase extends UseCase<UpdateAccountAddAmountCommand, Either<Notification, UpdateAccountAddAmountOutput>> {
}
