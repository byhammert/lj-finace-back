package com.lj.domain.account;

import com.lj.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class AccountID extends Identifier {
    
    private final String value;

    public AccountID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }
    
    public static AccountID unique() {
        return AccountID.from(UUID.randomUUID());
    }

    public static AccountID from(final String anId) {
        return new AccountID(anId);
    }

    public static AccountID from(UUID anId) {
        Objects.requireNonNull(anId);
        return new AccountID(anId.toString().toLowerCase());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AccountID that = (AccountID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
    
}
