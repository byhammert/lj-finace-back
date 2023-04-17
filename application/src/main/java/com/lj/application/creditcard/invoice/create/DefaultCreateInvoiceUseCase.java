package com.lj.application.creditcard.invoice.create;

import com.lj.domain.creditcard.invoice.Invoice;
import com.lj.domain.creditcard.invoice.InvoiceGateway;
import com.lj.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.Try;

public class DefaultCreateInvoiceUseCase extends CreateInvoiceUseCase {

    private final InvoiceGateway invoiceGateway;

    public DefaultCreateInvoiceUseCase(final InvoiceGateway invoiceGateway) {
        this.invoiceGateway = Objects.requireNonNull(invoiceGateway);
    }

    @Override
    public Either<Notification, CreateInvoiceOutput> execute(final CreateInvoiceCommand aCommand) {
        final var aCreditCard = aCommand.creditCard();
        final var aDueDate = aCommand.dueDate();

        final var notification = Notification.create();
        final var aInvoice = Invoice.newInvoice(
                aCreditCard, aDueDate);
        aInvoice.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(aInvoice);
    }

    private Either<Notification, CreateInvoiceOutput> create(final Invoice anInvoice) {
        return Try(() -> this.invoiceGateway.create(anInvoice))
                .toEither()
                .bimap(Notification::create, CreateInvoiceOutput::from);
    }
}
