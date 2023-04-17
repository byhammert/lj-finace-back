package com.lj.application.account.update.addamount;

import com.lj.domain.account.Account;
import com.lj.domain.account.AccountGateway;
import com.lj.domain.account.AccountID;
import com.lj.domain.exceptions.NotFoundException;
import com.lj.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.Supplier;

import static io.vavr.API.Try;

public class DefaultUpdateAccountAddAmountUseCase extends UpdateAccountAddAmountUseCase {

    private final AccountGateway accountGateway;

    public DefaultUpdateAccountAddAmountUseCase(AccountGateway accountGateway) {
        this.accountGateway = Objects.requireNonNull(accountGateway);
    }

    @Override
    public Either<Notification, UpdateAccountAddAmountOutput> execute(UpdateAccountAddAmountCommand aCommand) {
        final var anId = AccountID.from(aCommand.id());
        final var anAmount = aCommand.amount();

        final var notification = Notification.create();
        Account anAccount = accountGateway.findById(anId)
                .orElseThrow(notFound(anId));

        anAccount
                .addAmount(anAmount)
                .validate(notification);

        return notification.hasError() ? API.Left(notification) : update(anAccount);
    }


    private Supplier<NotFoundException> notFound(final AccountID anId) {
        return () -> NotFoundException.with(Account.class, anId);
    }

    private Either<Notification, UpdateAccountAddAmountOutput> update(final Account anAccount) {
        return Try(() -> this.accountGateway.update(anAccount))
                .toEither()
                .bimap(Notification::create, UpdateAccountAddAmountOutput::from);
    }
}
