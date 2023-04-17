package com.lj.domain.post.subcategory;

import com.lj.domain.validation.Error;
import com.lj.domain.validation.ValidationHandler;
import com.lj.domain.validation.Validator;

public class PostSubcategoryValidator extends Validator {

    private PostSubcategory postCategory;

    public PostSubcategoryValidator(final PostSubcategory aPostCategory, final ValidationHandler aHandler) {
        super(aHandler);
        this.postCategory = aPostCategory;
    }

    @Override
    public void validate() {
        checkNameConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.postCategory.getName();
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
            this.validationHandler().append(new Error("'name' must be between 3 and 255 characters"));
        }
    }
}
