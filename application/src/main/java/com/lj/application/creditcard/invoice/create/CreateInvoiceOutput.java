package com.lj.application.creditcard.invoice.create;

import com.lj.domain.creditcard.invoice.Invoice;

public record CreateInvoiceOutput(
        String id
) {
    public static CreateInvoiceOutput from(final String anId) {
        return new CreateInvoiceOutput(anId);
    }

    public static CreateInvoiceOutput from(final Invoice aInvoice) {
        return new CreateInvoiceOutput(aInvoice.getId().getValue());
    }
}
