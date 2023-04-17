package com.lj.application.account.update;

import com.lj.domain.account.Account;
import com.lj.domain.account.AccountID;

public record UpdateAccountOutput(
        AccountID id
) {
    public static UpdateAccountOutput from(final Account anAccount) {
        return new UpdateAccountOutput(anAccount.getId());
    }
}
