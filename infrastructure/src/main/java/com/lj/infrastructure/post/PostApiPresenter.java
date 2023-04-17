package com.lj.infrastructure.post;

import com.lj.application.account.retrieve.get.AccountOutput;
import com.lj.application.post.item.retrieve.get.PostItemOutput;
import com.lj.application.post.retrieve.list.PostsListOutput;
import com.lj.infrastructure.account.models.AccountApiOutput;
import com.lj.infrastructure.post.models.PostApiOutput;
import com.lj.infrastructure.post.models.PostsListApiOutput;

import java.util.function.Function;

public interface PostApiPresenter {

    Function<PostsListOutput, PostsListApiOutput> present =
            output -> new PostsListApiOutput(
                    output.id().getValue(),
                    output.description(),
                    output.effective(),
                    output.amount(),
                    output.postType(),
                    output.createdAt(),
                    output.updatedAt()
            );

    static PostsListApiOutput present(final PostsListOutput output) {
        return new PostsListApiOutput(
                output.id().getValue(),
                output.description(),
                output.effective(),
                output.amount(),
                output.postType(),
                output.createdAt(),
                output.updatedAt()
        );
    }

}
