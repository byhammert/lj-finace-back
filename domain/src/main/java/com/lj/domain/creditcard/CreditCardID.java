package com.lj.domain.creditcard;

import com.lj.domain.Identifier;
import com.lj.domain.post.PostID;

import java.util.Objects;
import java.util.UUID;

public class CreditCardID extends Identifier {

    private final String value;

    public CreditCardID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static CreditCardID unique() {
        return CreditCardID.from(UUID.randomUUID());
    }

    public static CreditCardID from(final String anId) {
        return new CreditCardID(anId);
    }

    public static CreditCardID from(UUID anId) {
        Objects.requireNonNull(anId);
        return new CreditCardID(anId.toString().toLowerCase());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CreditCardID that = (CreditCardID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
