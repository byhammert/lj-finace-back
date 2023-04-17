package com.lj.application.account.retrieve.get;

import com.lj.domain.account.Account;
import com.lj.domain.account.AccountGateway;
import com.lj.domain.account.AccountID;
import com.lj.domain.exceptions.DomainException;
import com.lj.domain.exceptions.NotFoundException;
import com.lj.domain.validation.Error;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetAccountByIdUseCase extends  GetAccountByIdUseCase {

    private final AccountGateway accountGateway;

    public DefaultGetAccountByIdUseCase(AccountGateway accountGateway) {
        this.accountGateway = Objects.requireNonNull(accountGateway);
    }

    @Override
    public AccountOutput execute(String anId) {
        final var anAccountId = AccountID.from(anId);
        return accountGateway.findById(anAccountId)
                .map(AccountOutput::from)
                .orElseThrow(notFound(anAccountId));
    }

    private Supplier<NotFoundException> notFound(final AccountID anId) {
        return () -> NotFoundException.with(Account.class, anId);
    }
}
