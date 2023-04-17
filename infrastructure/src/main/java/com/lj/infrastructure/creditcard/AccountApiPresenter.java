package com.lj.infrastructure.creditcard;

import com.lj.application.account.retrieve.get.AccountOutput;
import com.lj.infrastructure.account.models.AccountApiOutput;

import java.util.function.Function;

public interface AccountApiPresenter {

    Function<AccountOutput, AccountApiOutput> present =
            output -> new AccountApiOutput(
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

    static AccountApiOutput present(final AccountOutput output) {
        return new AccountApiOutput(
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
