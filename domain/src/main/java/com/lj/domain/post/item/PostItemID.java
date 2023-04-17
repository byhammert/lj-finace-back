package com.lj.domain.post.item;

import com.lj.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class PostItemID extends Identifier {

    private final String value;

    public PostItemID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static PostItemID unique() {
        return PostItemID.from(UUID.randomUUID());
    }

    public static PostItemID from(final String anId) {
        return new PostItemID(anId);
    }

    public static PostItemID from(UUID anId) {
        Objects.requireNonNull(anId);
        return new PostItemID(anId.toString().toLowerCase());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PostItemID that = (PostItemID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
