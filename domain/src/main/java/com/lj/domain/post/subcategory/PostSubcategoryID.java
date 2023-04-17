package com.lj.domain.post.subcategory;

import com.lj.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class PostSubcategoryID extends Identifier {

    private final String value;

    public PostSubcategoryID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static PostSubcategoryID unique() {
        return PostSubcategoryID.from(UUID.randomUUID());
    }

    public static PostSubcategoryID from(final String anId) {
        return new PostSubcategoryID(anId);
    }

    public static PostSubcategoryID from(UUID anId) {
        Objects.requireNonNull(anId);
        return new PostSubcategoryID(anId.toString().toLowerCase());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PostSubcategoryID that = (PostSubcategoryID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
