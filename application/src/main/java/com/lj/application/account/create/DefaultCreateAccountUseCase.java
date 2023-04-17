package com.lj.application.account.create;

import com.lj.domain.account.Account;
import com.lj.domain.account.AccountGateway;
import com.lj.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.Try;

public class DefaultCreateAccountUseCase extends CreateAccountUseCase {

    private final AccountGateway accountGateway;

    public DefaultCreateAccountUseCase(final AccountGateway accountGateway) {
        this.accountGateway = Objects.requireNonNull(accountGateway);
    }

    @Override
    public Either<Notification, CreateAccountOutput> execute(final CreateAccountCommand aCommand) {
        final var aName = aCommand.name();
        final var aCategory = aCommand.category();
        final var anUser = aCommand.user();
        final var aSymbol = aCommand.symbol();
        final var aBalance = aCommand.balance();
        final var isActive = aCommand.isActive();

        final var notification = Notification.create();
        final var anAccount = Account.newAccount(aName, anUser, aCategory, aSymbol, aBalance, isActive);
        anAccount.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(anAccount);
    }

    private Either<Notification, CreateAccountOutput> create(final Account anAccount) {
        return Try(() -> this.accountGateway.create(anAccount))
                .toEither()
                .bimap(Notification::create, CreateAccountOutput::from);
    }
}
