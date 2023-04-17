package com.lj.domain.account;

import com.lj.domain.validation.Error;
import com.lj.domain.validation.ValidationHandler;
import com.lj.domain.validation.Validator;

public class AccountValidator  extends Validator {

    private Account account;

    public AccountValidator(final Account anAccount, final ValidationHandler aHandler) {
        super(aHandler);
        this.account = anAccount;
    }

    @Override
    public void validate() {
        checkNameConstraints();
        checkCategoryConstraints();
        checkSymbolConstraints();
        checkUserConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.account.getName();
        if (name == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }

        final int length = name.trim().length();
        if (length > 20 || length < 3) {
            this.validationHandler().append(new Error("'name' must be between 3 and 20 characters"));
        }
    }

    private void checkCategoryConstraints() {
        final var category = this.account.getCategory();
        if (category == null) {
            this.validationHandler().append(new Error("'category' should not be null"));
            return;
        }

        if (category.isBlank()) {
            this.validationHandler().append(new Error("'category' should not be empty"));
            return;
        }

        final int length = category.trim().length();
        if (length > 20 || length < 3) {
            this.validationHandler().append(new Error("'category' must be between 3 and 20 characters"));
        }
    }

    private void checkSymbolConstraints() {
        final var symbol = this.account.getSymbol();
        if (symbol == null) {
            this.validationHandler().append(new Error("'symbol' should not be null"));
            return;
        }

        if (symbol.isBlank()) {
            this.validationHandler().append(new Error("'symbol' should not be empty"));
            return;
        }

        final int length = symbol.trim().length();
        if (length > 20) {
            this.validationHandler().append(new Error("'symbol' must be a maximum of 20 characters"));
        }
    }

    private void checkUserConstraints() {
        final var user = this.account.getUser();
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
}
