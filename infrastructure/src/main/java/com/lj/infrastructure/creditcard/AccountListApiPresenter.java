package com.lj.infrastructure.creditcard;

import com.lj.application.account.retrieve.list.AccountListOutput;
import com.lj.infrastructure.account.models.AccountListApiOutput;

public interface AccountListApiPresenter {


    static AccountListApiOutput present(final AccountListOutput output) {
        return new AccountListApiOutput(
          output.id().getValue(),
                output.name(),
                output.category(),
                output.symbol(),
                output.balance(),
                output.isActive(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }
}
