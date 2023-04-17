package com.lj.domain.creditcard.invoice;

import com.lj.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class InvoiceID extends Identifier {

    private final String value;

    public InvoiceID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static InvoiceID unique() {
        return InvoiceID.from(UUID.randomUUID());
    }

    public static InvoiceID from(final String anId) {
        return new InvoiceID(anId);
    }

    public static InvoiceID from(UUID anId) {
        Objects.requireNonNull(anId);
        return new InvoiceID(anId.toString().toLowerCase());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final InvoiceID that = (InvoiceID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
