package com.lj.application.account.create;

import com.lj.domain.account.Account;
import com.lj.domain.account.AccountID;

public record CreateAccountOutput(
        String id
) {
    public static CreateAccountOutput from(final String anId) {
        return new CreateAccountOutput(anId);
    }

    public static CreateAccountOutput from(final Account anAccount) {
        return new CreateAccountOutput(anAccount.getId().getValue());
    }
}
