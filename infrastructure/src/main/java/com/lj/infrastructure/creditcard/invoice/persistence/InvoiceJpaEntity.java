package com.lj.infrastructure.creditcard.invoice.persistence;

import com.lj.domain.creditcard.invoice.Invoice;
import com.lj.domain.creditcard.invoice.InvoiceID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "invoice")
public class InvoiceJpaEntity {
    @Id
    private String id;
    @Column(name = "credit_card", nullable = false)
    private String creditCard;
    @Column(name = "amount")
    private long amount;
    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;
    @Column(name = "effective")
    private boolean effective;
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public InvoiceJpaEntity() {
    }

    public InvoiceJpaEntity(final String anId,
                            final String aCreditCard,
                            final long anAmount,
                            final LocalDate aDueDate,
                            final boolean isEffective,
                            final Instant aCreationDate,
                            final Instant anUpdateDate) {
        this.id = anId;
        this.creditCard = aCreditCard;
        this.amount = anAmount;
        this.dueDate = aDueDate;
        this.effective = isEffective;
        this.createdAt = aCreationDate;
        this.updatedAt = anUpdateDate;
    }

    public  static InvoiceJpaEntity from(final Invoice aInvoice) {
        return new InvoiceJpaEntity(aInvoice.getId().getValue(),
                                    aInvoice.getCreditCard(),
                                    aInvoice.getAmount(),
                                    aInvoice.getDueDate(),
                                    aInvoice.isEffective(),
                                    aInvoice.getCreatedAt(),
                                    aInvoice.getUpdatedAt());
    }

    public Invoice toAggregate() {
        return Invoice.with(InvoiceID.from(id),
                            creditCard,
                            amount,
                            dueDate,
                            effective,
                            createdAt,
                            updatedAt);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isEffective() {
        return effective;
    }

    public void setEffective(boolean effective) {
        this.effective = effective;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
