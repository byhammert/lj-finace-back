package com.lj.application.account.update;

import com.lj.application.account.create.CreateAccountCommand;

public record UpdateAccountCommand(
        String id,
        String name,
        String category,
        String symbol,
        long balance,
        boolean isActive
) {

    public static UpdateAccountCommand with(
                                            final String anId,
                                            final String aName,
                                            final String aCategory,
                                            final String aSymbol,
                                            final long aBalance,
                                            final boolean isActive) {
        return new UpdateAccountCommand(anId,
                aName,
                aCategory,
                aSymbol,
                aBalance,
                isActive);
    }
}
