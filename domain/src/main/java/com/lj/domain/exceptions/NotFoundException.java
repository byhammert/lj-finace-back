package com.lj.domain.exceptions;

import com.lj.domain.AggregateRoot;
import com.lj.domain.Identifier;
import com.lj.domain.validation.Error;

import java.util.Collections;
import java.util.List;

public class NotFoundException extends DomainException {
    protected NotFoundException(final String aMessage, final List<Error> anErrors) {
        super(aMessage, anErrors);
    }

    public static NotFoundException with (
            final Class<? extends AggregateRoot<?>> anAggregate,
            final Identifier id) {
        final var anError = "%s with ID %s was not found".formatted(
                anAggregate.getSimpleName(),
                id.getValue()
        );
        return new NotFoundException(anError, Collections.emptyList());
    }
}
