package com.lj.application.creditcard.invoice.create;

import java.time.LocalDate;

public record CreateInvoiceCommand(
        String creditCard,
        LocalDate dueDate
) {

    public static CreateInvoiceCommand with(final String aCreditCard,
                                            final LocalDate aDueDate) {
        return new CreateInvoiceCommand(aCreditCard, aDueDate);
    }

}
