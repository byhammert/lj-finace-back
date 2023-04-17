package com.lj.domain.post.category;

import com.lj.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class PostCategoryID extends Identifier {

    private final String value;

    public PostCategoryID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static PostCategoryID unique() {
        return PostCategoryID.from(UUID.randomUUID());
    }

    public static PostCategoryID from(final String anId) {
        return new PostCategoryID(anId);
    }

    public static PostCategoryID from(UUID anId) {
        Objects.requireNonNull(anId);
        return new PostCategoryID(anId.toString().toLowerCase());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PostCategoryID that = (PostCategoryID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
