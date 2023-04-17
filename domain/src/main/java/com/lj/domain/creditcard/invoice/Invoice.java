package com.lj.domain.creditcard.invoice;

import com.lj.domain.AggregateRoot;
import com.lj.domain.validation.ValidationHandler;

import java.time.Instant;
import java.time.LocalDate;

public class Invoice extends AggregateRoot<InvoiceID> implements Cloneable {

    private String creditCard;
    private long amount;
    private LocalDate dueDate;
    private boolean effective;
    private Instant createdAt;
    private Instant updatedAt;

    public Invoice(final InvoiceID anInvoiceID,
                   final String aCreditCard,
                   final long anAmount,
                   final LocalDate aDueDate,
                   final boolean isEffective,
                   final Instant aCreationDate,
                   final Instant anUpdateDate) {
        super(anInvoiceID);
        this.creditCard = aCreditCard;
        this.amount = anAmount;
        this.dueDate = aDueDate;
        this.effective = isEffective;
        this.createdAt = aCreationDate;
        this.updatedAt = anUpdateDate;
    }

    public static Invoice newInvoice(final String aCreditCard,
                                        final LocalDate aDueDate) {
        final var id = InvoiceID.unique();
        final var now = Instant.now();
        return new Invoice(id,
                            aCreditCard,
                            0l,
                            aDueDate,
                            false,
                            now,
                            now);
    }

    public static Invoice with( final InvoiceID anId,
                                final String aCreditCard,
                                final long anAmount,
                                final LocalDate aDueDate,
                                final boolean isEffective,
                                final Instant aCreatedAt,
                                final Instant anUpdatedAt) {
        return new Invoice(anId,
                            aCreditCard,
                            anAmount,
                            aDueDate,
                            isEffective,
                            aCreatedAt,
                            anUpdatedAt);
    }

    public Invoice effective() {
        this.effective = true;
        this.updatedAt = Instant.now();
        return this;
    }

    public Invoice updateAmount(long anAmount) {
        this.amount = amount + anAmount;
        this.updatedAt = Instant.now();
        return this;
    }

    @Override
    public void validate(ValidationHandler aHandler) {
        new InvoiceValidator(this, aHandler).validate();
    }

    @Override
    public Invoice clone() {
        try {
            return (Invoice) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
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
