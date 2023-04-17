package com.lj.application.account.retrieve.list;

import com.lj.domain.account.AccountGateway;

import java.util.List;
import java.util.Objects;

public class DefaultListAccountsUseCase extends ListAccountsUseCase {

    private final AccountGateway accountGateway;

    public DefaultListAccountsUseCase(AccountGateway accountGateway) {
        this.accountGateway = Objects.requireNonNull(accountGateway);
    }

    @Override
    public List<AccountListOutput> execute(String anId) {
        return this.accountGateway.findAllByUserOrderByNameAsc(anId).stream()
                .map(AccountListOutput::from)
                .toList();
    }
}
