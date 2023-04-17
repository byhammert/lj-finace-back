package com.lj.domain.post.item;

import com.lj.domain.validation.Error;
import com.lj.domain.validation.ValidationHandler;
import com.lj.domain.validation.Validator;

public class PostItemValidator extends Validator {

    private PostItem postItem;

    public PostItemValidator(final PostItem aPostItem, final ValidationHandler aHandler) {
        super(aHandler);
        this.postItem = aPostItem;
    }

    @Override
    public void validate() {
        checkPostConstraints();
        checkDueDateConstraints();
    }

    private void checkPostConstraints() {
        final var post = this.postItem.getPost();
        if (post == null) {
            this.validationHandler().append(new Error("'post' should not be null"));
            return;
        }

        if (post.isBlank()) {
            this.validationHandler().append(new Error("'post' should not be empty"));
            return;
        }
    }

    private void checkDueDateConstraints() {
        final var dueDate = this.postItem.getDueDate();
        if (dueDate == null) {
            this.validationHandler().append(new Error("'dueDate' should not be null"));
            return;
        }
    }
}
