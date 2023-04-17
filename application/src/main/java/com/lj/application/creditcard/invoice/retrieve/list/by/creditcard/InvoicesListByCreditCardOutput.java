package com.lj.application.creditcard.invoice.retrieve.list.by.creditcard;

import com.lj.domain.creditcard.invoice.Invoice;
import com.lj.domain.creditcard.invoice.InvoiceID;

import java.time.Instant;
import java.time.LocalDate;

public record InvoicesListByCreditCardOutput(
        InvoiceID id,
        String creditCard,
        long amount,
        LocalDate dueDate,
        boolean effective,
        Instant createdAt,
        Instant updatedAt
) {
    public static InvoicesListByCreditCardOutput from(final Invoice aInvoice) {
        return new InvoicesListByCreditCardOutput(
                aInvoice.getId(),
                aInvoice.getCreditCard(),
                aInvoice.getAmount(),
                aInvoice.getDueDate(),
                aInvoice.isEffective(),
                aInvoice.getCreatedAt(),
                aInvoice.getUpdatedAt()
        );
    }
}
