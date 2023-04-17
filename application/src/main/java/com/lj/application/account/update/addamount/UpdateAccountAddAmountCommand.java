package com.lj.application.account.update.addamount;

public record UpdateAccountAddAmountCommand(
        String id,
        long amount
) {

    public static UpdateAccountAddAmountCommand with(
                                            final String anId,
                                            final long amount) {
        return new UpdateAccountAddAmountCommand(anId,
                amount);
    }
}
