package com.lj.application.account.update;

import com.lj.domain.account.Account;
import com.lj.domain.account.AccountGateway;
import com.lj.domain.account.AccountID;
import com.lj.domain.exceptions.DomainException;
import com.lj.domain.exceptions.NotFoundException;
import com.lj.domain.validation.Error;
import com.lj.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.Supplier;

import static io.vavr.API.Try;

public class DefaultUpdateAccountUseCase extends UpdateAccountUseCase {

    private final AccountGateway accountGateway;

    public DefaultUpdateAccountUseCase(AccountGateway accountGateway) {
        this.accountGateway = Objects.requireNonNull(accountGateway);
    }

    @Override
    public Either<Notification, UpdateAccountOutput> execute(UpdateAccountCommand aCommand) {
        final var anId = AccountID.from(aCommand.id());
        final var aName = aCommand.name();
        final var aCategory = aCommand.category();
        final var aSymbol = aCommand.symbol();
        final var aBalance = aCommand.balance();
        final var isActive = aCommand.isActive();

        final var notification = Notification.create();
        Account anAccount = accountGateway.findById(anId)
                .orElseThrow(notFound(anId));

        anAccount
                .update(aName, aCategory, aSymbol, aBalance, isActive)
                .validate(notification);

        return notification.hasError() ? API.Left(notification) : update(anAccount);
    }


    private Supplier<NotFoundException> notFound(final AccountID anId) {
        return () -> NotFoundException.with(Account.class, anId);
    }

    private Either<Notification, UpdateAccountOutput> update(final Account anAccount) {
        return Try(() -> this.accountGateway.update(anAccount))
                .toEither()
                .bimap(Notification::create, UpdateAccountOutput::from);
    }
}
