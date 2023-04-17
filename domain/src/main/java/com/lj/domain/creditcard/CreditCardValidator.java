package com.lj.domain.creditcard;

import com.lj.domain.validation.Error;
import com.lj.domain.validation.ValidationHandler;
import com.lj.domain.validation.Validator;

public class CreditCardValidator extends Validator {

    private CreditCard creditCard;

    public CreditCardValidator(final CreditCard aCreditCard, final ValidationHandler aHandler) {
        super(aHandler);
        this.creditCard = aCreditCard;
    }

    @Override
    public void validate() {
        checkDescriptionConstraints();
        checkAccountConstraints();
        checkUserConstraints();
        checkClosingDayConstraints();
        checkDueDayConstraints();
        checkLimitConstraints();
    }

    private void checkDescriptionConstraints() {
        final var description = this.creditCard.getDescription();
        if (description == null) {
            this.validationHandler().append(new Error("'description' should not be null"));
            return;
        }

        if (description.isBlank()) {
            this.validationHandler().append(new Error("'description' should not be empty"));
            return;
        }

        final int length = description.trim().length();
        if (length > 20 || length < 3) {
            this.validationHandler().append(new Error("'description' must be between 3 and 255 characters"));
        }
    }

    private void checkAccountConstraints() {
        final var account = this.creditCard.getAccount();
        if (account == null) {
            this.validationHandler().append(new Error("'account' should not be null"));
            return;
        }

        if (account.isBlank()) {
            this.validationHandler().append(new Error("'account' should not be empty"));
            return;
        }
    }

    private void checkUserConstraints() {
        final var user = this.creditCard.getUser();
        if (user == null) {
            this.validationHandler().append(new Error("'user' should not be null"));
            return;
        }

        if (user.isBlank()) {
            this.validationHandler().append(new Error("'user' should not be empty"));
            return;
        }

        final int length = user.trim().length();
        if (length > 255) {
            this.validationHandler().append(new Error("'user' must be a maximum of 255 characters"));
        }
    }

    private void checkClosingDayConstraints() {
        final var closingDay = this.creditCard.getClosingDay();

        if (closingDay < 0) {
            this.validationHandler().append(new Error("'closingDay' should not be negative"));
            return;
        }
    }

    private void checkDueDayConstraints() {
        final var dueDay = this.creditCard.getDueDay();

        if (dueDay < 0) {
            this.validationHandler().append(new Error("'dueDay' should not be negative"));
            return;
        }
    }

    private void checkLimitConstraints() {
        final var limit = this.creditCard.getLimit();

        if (limit < 1) {
            this.validationHandler().append(new Error("'dueDay' should be greater than 0"));
            return;
        }
    }
}
