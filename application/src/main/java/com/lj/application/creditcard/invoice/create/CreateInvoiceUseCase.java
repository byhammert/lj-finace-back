package com.lj.application.creditcard.invoice.create;

import com.lj.application.UseCase;
import com.lj.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateInvoiceUseCase extends UseCase<CreateInvoiceCommand, Either<Notification, CreateInvoiceOutput>> {

}
