package com.lj.domain.post.category;

import com.lj.domain.post.item.PostItem;
import com.lj.domain.validation.Error;
import com.lj.domain.validation.ValidationHandler;
import com.lj.domain.validation.Validator;

public class PostCategoryValidator extends Validator {

    private PostCategory postCategory;

    public PostCategoryValidator(final PostCategory aPostCategory, final ValidationHandler aHandler) {
        super(aHandler);
        this.postCategory = aPostCategory;
    }

    @Override
    public void validate() {
        checkNameConstraints();
        checkUserConstraints();
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

    private void checkUserConstraints() {
        final var user = this.postCategory.getUser();
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
