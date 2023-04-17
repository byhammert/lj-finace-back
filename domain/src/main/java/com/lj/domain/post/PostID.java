package com.lj.domain.post;

import com.lj.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class PostID extends Identifier {
    
    private final String value;

    public PostID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static PostID unique() {
        return PostID.from(UUID.randomUUID());
    }

    public static PostID from(final String anId) {
        return new PostID(anId);
    }

    public static PostID from(UUID anId) {
        Objects.requireNonNull(anId);
        return new PostID(anId.toString().toLowerCase());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PostID that = (PostID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
