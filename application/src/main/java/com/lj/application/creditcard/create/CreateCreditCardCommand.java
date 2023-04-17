package com.lj.application.creditcard.create;

public record CreateCreditCardCommand(
        String description,
        String account,
        String user,
        int closingDay,
        int dueDay,
        long limit
) {

    public static CreateCreditCardCommand with(final String aDescription,
                                               final String anAccount,
                                               final String anUser,
                                               final int aClosingDay,
                                               final int aDueDay,
                                               final long aLimit) {
        return new CreateCreditCardCommand(aDescription,
                                            anAccount,
                                            anUser,
                                            aClosingDay,
                                            aDueDay,
                                            aLimit);
    }

}
