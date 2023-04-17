package com.lj.application.creditcard.create;

import com.lj.domain.creditcard.CreditCard;

public record CreateCreditCardOutput(
        String id
) {
    public static CreateCreditCardOutput from(final String anId) {
        return new CreateCreditCardOutput(anId);
    }

    public static CreateCreditCardOutput from(final CreditCard aCreditCard) {
        return new CreateCreditCardOutput(aCreditCard.getId().getValue());
    }
}
