package com.lj.domain.creditcard.invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceGateway {

    Invoice create(Invoice aInvoice);
    void deleteById(InvoiceID anId);
    Optional<Invoice> findById(InvoiceID anId);
    Invoice update(Invoice aInvoice);
    List<Invoice> findAllByCreditCard(String aCreditCard);


}
