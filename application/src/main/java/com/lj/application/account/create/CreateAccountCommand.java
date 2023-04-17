package com.lj.application.account.create;

public record CreateAccountCommand(
        String name,
        String user,
        String category,
        String symbol,
        long balance,
        boolean isActive
) {

    public static CreateAccountCommand with(final String aName,
                                            final String anUser,
                                            final String aCategory,
                                            final String aSymbol,
                                            final long aBalance,
                                            final boolean isActive) {
        return new CreateAccountCommand(aName,
                                        anUser,
                                        aCategory,
                                        aSymbol,
                                        aBalance,
                                        isActive);
    }

}
