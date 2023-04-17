package com.lj.application.post.create.expense;

import com.lj.application.account.retrieve.get.AccountOutput;
import com.lj.application.account.retrieve.get.GetAccountByIdUseCase;
import com.lj.application.account.update.addamount.UpdateAccountAddAmountCommand;
import com.lj.application.account.update.addamount.UpdateAccountAddAmountUseCase;
import com.lj.application.post.category.retrieve.get.GetPostCategoryByIdUseCase;
import com.lj.application.post.item.create.CreateListPostItemCommand;
import com.lj.application.post.item.create.CreateListPostItemUseCase;
import com.lj.application.post.subcategory.retrive.get.GetPostSubcategoryByIdUseCase;
import com.lj.domain.post.Post;
import com.lj.domain.post.PostGateway;
import com.lj.domain.post.PostType;
import com.lj.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.time.LocalDate;
import java.util.Objects;

import static io.vavr.API.Try;

public class DefaultCreateExpensePostUseCase extends CreateExpensePostUseCase {

    private final PostGateway postGateway;
    private final GetPostCategoryByIdUseCase getPostCategoryByIdUseCase;
    private final GetPostSubcategoryByIdUseCase getPostSubcategoryByIdUseCase;
    private final GetAccountByIdUseCase getAccountByIdUseCase;

    private final UpdateAccountAddAmountUseCase updateAccountBalanceUseCase;

    private final CreateListPostItemUseCase createListPostItemUseCase;

    public DefaultCreateExpensePostUseCase(final PostGateway postGateway,
                                           final GetPostCategoryByIdUseCase getPostCategoryByIdUseCase,
                                           final GetPostSubcategoryByIdUseCase getPostSubcategoryByIdUseCase,
                                           final GetAccountByIdUseCase getAccountByIdUseCase,
                                           final CreateListPostItemUseCase createListPostItemUseCase,
                                           final UpdateAccountAddAmountUseCase updateAccountBalanceUseCase) {
        this.postGateway = Objects.requireNonNull(postGateway);
        this.getPostCategoryByIdUseCase = Objects.requireNonNull(getPostCategoryByIdUseCase);
        this.getPostSubcategoryByIdUseCase = Objects.requireNonNull(getPostSubcategoryByIdUseCase);
        this.getAccountByIdUseCase = Objects.requireNonNull(getAccountByIdUseCase);
        this.createListPostItemUseCase = Objects.requireNonNull(createListPostItemUseCase);
        this.updateAccountBalanceUseCase = Objects.requireNonNull(updateAccountBalanceUseCase);
    }

    @Override
    public Either<Notification, CreateExpensePostOutput> execute(CreateExpensePostCommand aCommand) {
        final var aDescription = aCommand.description();
        final var aCategory = aCommand.category();
        final var aSubcategory = aCommand.subcategory();
        final var aRecurrence = aCommand.recurrence();
        final var anAccount = aCommand.account();
        final var anUser = aCommand.user();
        final var aNote = aCommand.note();
        final var isEffective = aCommand.effective();
        final var anAmount = aCommand.amount();
        final var anInstallment = aCommand.installment();
        final var aDueDate = aCommand.dueDate();

        final var notification = Notification.create();

        this.validate(aCategory, aSubcategory, anAccount);

        final var actualAccount = getAccountByIdUseCase.execute(anAccount);

        final var aPost = Post.newPost(aDescription,
                                            aCategory,
                                            aSubcategory,
                                            aRecurrence,
                                            anAccount,
                                            anUser,
                                            aNote,
                                            PostType.EXPENSE,
                                            isEffective,
                                            anAmount);

        aPost.validate(notification);

        this.createPostItems(aPost.getId().getValue(),
                                anInstallment,
                                anAmount,
                                aDueDate);

        if (isEffective) {
            this.updateAccount(actualAccount, anAmount);
        }

        return notification.hasError() ? API.Left(notification) : create(aPost);
    }

    private void updateAccount(final AccountOutput accountOutput, final long postAmount) {
        final UpdateAccountAddAmountCommand updateAccountAddAmountCommand = UpdateAccountAddAmountCommand.with(
                accountOutput.id().getValue(),
                -postAmount);

        this.updateAccountBalanceUseCase.execute(updateAccountAddAmountCommand);
    }

    private void createPostItems(final String aPost,
                                 final int anInstallment,
                                 final long anAmount,
                                 LocalDate aDueDate) {
        final var aCommand = CreateListPostItemCommand.with(aPost,
                                                                                    anInstallment,
                                                                                    anAmount,
                                                                                    aDueDate);
        createListPostItemUseCase.execute(aCommand);
    }

    private void validate(final String aCategory, final String aSubcategory, final String anAccount) {
        getPostCategoryByIdUseCase.execute(aCategory);
        getPostSubcategoryByIdUseCase.execute(aSubcategory);
    }

    private Either<Notification, CreateExpensePostOutput> create(final Post aPost) {
        return Try(() -> this.postGateway.create(aPost))
                .toEither()
                .bimap(Notification::create, CreateExpensePostOutput::from);
    }
}
