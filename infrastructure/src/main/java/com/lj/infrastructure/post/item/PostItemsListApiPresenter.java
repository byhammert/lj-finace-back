package com.lj.infrastructure.post.item;

import com.lj.application.post.item.retrieve.list.PostItemsListOutput;
import com.lj.infrastructure.post.item.models.PostItemsListApiOutput;

public interface PostItemsListApiPresenter {

    static PostItemsListApiOutput present(final PostItemsListOutput output) {
        return new PostItemsListApiOutput(
          output.id().getValue(),
                output.post(),
                output.number(),
                output.amount(),
                output.effective(),
                output.paymentDate(),
                output.dueDate()
        );
    }
}
