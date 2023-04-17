package com.lj.infrastructure.creditcard.invoice.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<InvoiceJpaEntity, String> {

}
