package com.lj.application.account.update.addamount;

import com.lj.domain.account.Account;
import com.lj.domain.account.AccountID;

public record UpdateAccountAddAmountOutput(
        AccountID id
) {
    public static UpdateAccountAddAmountOutput from(final Account anAccount) {
        return new UpdateAccountAddAmountOutput(anAccount.getId());
    }
}
