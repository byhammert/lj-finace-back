package com.lj.domain.creditcard.invoice;

import com.lj.domain.validation.Error;
import com.lj.domain.validation.ValidationHandler;
import com.lj.domain.validation.Validator;

public class InvoiceValidator extends Validator {

    private Invoice invoice;

    public InvoiceValidator(final Invoice anInvoice, final ValidationHandler aHandler) {
        super(aHandler);
        this.invoice = anInvoice;
    }

    @Override
    public void validate() {
        checkCreditCardConstraints();
        checkDueDateConstraints();
        checkAmountConstraints();
    }

    private void checkCreditCardConstraints() {
        final var creditCard = this.invoice.getCreditCard();
        if (creditCard == null) {
            this.validationHandler().append(new Error("'creditCard' should not be null"));
            return;
        }

        if (creditCard.isBlank()) {
            this.validationHandler().append(new Error("'creditCard' should not be empty"));
            return;
        }
    }

    private void checkDueDateConstraints() {
        final var dueDate = this.invoice.getDueDate();
        if (dueDate == null) {
            this.validationHandler().append(new Error("'dueDate' should not be null"));
            return;
        }
    }

    private void checkAmountConstraints() {
        final var amount = this.invoice.getAmount();

        if (amount < 0) {
            this.validationHandler().append(new Error("'amount' should not be negative"));
            return;
        }
    }
}
