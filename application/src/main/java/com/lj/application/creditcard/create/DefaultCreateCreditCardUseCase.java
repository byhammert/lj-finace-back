package com.lj.application.creditcard.create;

import com.lj.domain.creditcard.CreditCard;
import com.lj.domain.creditcard.CreditCardGateway;
import com.lj.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.Try;

public class DefaultCreateCreditCardUseCase extends CreateCreditCardUseCase {

    private final CreditCardGateway creditCardGateway;

    public DefaultCreateCreditCardUseCase(final CreditCardGateway creditCardGateway) {
        this.creditCardGateway = Objects.requireNonNull(creditCardGateway);
    }

    @Override
    public Either<Notification, CreateCreditCardOutput> execute(final CreateCreditCardCommand aCommand) {
        final var aDescription = aCommand.description();
        final var anAccount = aCommand.account();
        final var anUser = aCommand.user();
        final var aClosingDay = aCommand.closingDay();
        final var aDueDay = aCommand.dueDay();
        final var aLimit = aCommand.limit();

        final var notification = Notification.create();
        final var aCreditCard = CreditCard.newCreditCard(
                aDescription, anAccount, anUser, aClosingDay, aDueDay, aLimit);
        aCreditCard.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(aCreditCard);
    }

    private Either<Notification, CreateCreditCardOutput> create(final CreditCard aCreditCard) {
        return Try(() -> this.creditCardGateway.create(aCreditCard))
                .toEither()
                .bimap(Notification::create, CreateCreditCardOutput::from);
    }
}
