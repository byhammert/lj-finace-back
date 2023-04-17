package com.lj.application.creditcard.invoice.retrieve.list.by.creditcard;

import com.lj.domain.creditcard.invoice.InvoiceGateway;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultListInvoicesByCreditCardUseCase extends ListInvoicesByCreditCardUseCase {

    private final InvoiceGateway invoiceGateway;

    public DefaultListInvoicesByCreditCardUseCase(final InvoiceGateway invoiceGateway) {
        this.invoiceGateway = Objects.requireNonNull(invoiceGateway);
    }

    @Override
    public List<InvoicesListByCreditCardOutput> execute(final String aCreditCard) {
        return this.invoiceGateway.findAllByCreditCard(aCreditCard)
                .stream().map(InvoicesListByCreditCardOutput::from)
                .collect(Collectors.toList());
    }
}
