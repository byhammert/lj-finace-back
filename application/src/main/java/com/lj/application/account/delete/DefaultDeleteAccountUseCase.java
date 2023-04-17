package com.lj.application.account.delete;

import com.lj.domain.account.AccountGateway;
import com.lj.domain.account.AccountID;

import java.util.Objects;

public class DefaultDeleteAccountUseCase extends DeleteAccountUseCase {

    private final AccountGateway accountGateway;

    public DefaultDeleteAccountUseCase(AccountGateway accountGateway) {
        this.accountGateway = Objects.requireNonNull(accountGateway);
    }

    @Override
    public void execute(String anId) {
        this.accountGateway.deleteById(AccountID.from(anId));
    }
}
