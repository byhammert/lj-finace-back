package com.lj.domain.post;

import com.lj.domain.post.Post;
import com.lj.domain.validation.Error;
import com.lj.domain.validation.ValidationHandler;
import com.lj.domain.validation.Validator;

public class PostValidator extends Validator {

    private Post post;

    public PostValidator(final Post aPost, final ValidationHandler aHandler) {
        super(aHandler);
        this.post = aPost;
    }

    @Override
    public void validate() {
        checkDescriptionConstraints();
        checkCategoryConstraints();
        checkSubcategoryConstraints();
        checkAccountConstraints();
        checkUserConstraints();
        checkNoteConstraints();
        checkPostTypeConstraints();
        checkAmountConstraints();
        checkRecurrenceConstraints();
    }

    private void checkDescriptionConstraints() {
        final var description = this.post.getDescription();
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

    private void checkCategoryConstraints() {
        final var category = this.post.getCategory();
        if (category == null) {
            this.validationHandler().append(new Error("'category' should not be null"));
            return;
        }

        if (category.isBlank()) {
            this.validationHandler().append(new Error("'category' should not be empty"));
            return;
        }
    }

    private void checkSubcategoryConstraints() {
        final var subcategory = this.post.getSubcategory();
        if (subcategory == null) {
            this.validationHandler().append(new Error("'subcategory' should not be null"));
            return;
        }

        if (subcategory.isBlank()) {
            this.validationHandler().append(new Error("'subcategory' should not be empty"));
            return;
        }
    }

    private void checkAmountConstraints() {
        final var amount = this.post.getAmount();

        if (amount < 0) {
            this.validationHandler().append(new Error("'amount' should not be negative"));
            return;
        }
    }

    private void checkAccountConstraints() {
        final var account = this.post.getAccount();
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
        final var user = this.post.getUser();
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

    private void checkNoteConstraints() {
        final var note = this.post.getNote();
        if (null != note && !"".equals(note)) {
            final int length = note.trim().length();
            if (length > 4000) {
                this.validationHandler().append(new Error("'user' must be a maximum of 4000 characters"));
            }
        }
    }

    private void checkPostTypeConstraints() {
        final var postType = this.post.getPostType();
        if (null == postType) {
            this.validationHandler().append(new Error("'postType' should not be null"));
        }
    }

    private void checkRecurrenceConstraints() {
        final var recurrence = this.post.getRecurrence();
        if (null == recurrence) {
            this.validationHandler().append(new Error("'recurrence' should not be null"));
        }
    }


}
