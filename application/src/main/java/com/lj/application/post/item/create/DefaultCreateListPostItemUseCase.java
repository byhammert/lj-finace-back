package com.lj.application.post.item.create;

import com.lj.domain.post.item.PostItem;
import com.lj.domain.post.item.PostItemGateway;
import com.lj.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static io.vavr.API.Try;

public class DefaultCreateListPostItemUseCase extends CreateListPostItemUseCase {

    private final PostItemGateway postItemGateway;

    public DefaultCreateListPostItemUseCase(final PostItemGateway postItemGateway) {
        this.postItemGateway = Objects.requireNonNull(postItemGateway);
    }

    @Override
    public Either<Notification, CreateListPostItemOutput> execute(final CreateListPostItemCommand aCommand) {
        final var aPost = aCommand.post();
        final var anInstallment = aCommand.installment();
        final var anAmount = aCommand.amount();
        final var aDueDate = aCommand.dueDate();

        final var notification = Notification.create();
        final var aPostItems = this.buildListPostItem(aPost,
                                                                    anInstallment,
                                                                    anAmount,
                                                                    aDueDate,
                                                                    notification);


        return notification.hasError() ? API.Left(notification) : create(aPostItems);
    }

    private List<PostItem> buildListPostItem(final String aPost,
                                             final int anInstallments,
                                             final long anAmount,
                                             final LocalDate aDueDate,
                                             Notification aNotification) {
        final long itemAmount = anAmount / anInstallments;
        List<PostItem> postItems = new ArrayList<>();
        LocalDate dueDate = aDueDate.minusMonths(1);

        for (int installment = 1; installment <= anInstallments; installment++) {
            dueDate.plusMonths(1);
            PostItem postItem = PostItem.newPostItem(aPost,
                                                    installment,
                                                    itemAmount,
                                                    false,
                                                    null,
                                                    dueDate,
                                                    null);

            postItem.validate(aNotification);
            postItems.add(postItem);
        }

        return postItems;
    }

    private Either<Notification, CreateListPostItemOutput> create(final List<PostItem> aPostItems) {
        return Try(() -> this.postItemGateway.create(aPostItems))
                .toEither()
                .bimap(Notification::create, CreateListPostItemOutput::from);
    }
}
