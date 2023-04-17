package com.lj.infrastructure.api.controllers;

import com.lj.application.post.create.expense.CreateExpensePostCommand;
import com.lj.application.post.create.expense.CreateExpensePostOutput;
import com.lj.application.post.create.expense.CreateExpensePostUseCase;
import com.lj.application.post.create.revenue.CreateRevenuePostCommand;
import com.lj.application.post.create.revenue.CreateRevenuePostOutput;
import com.lj.application.post.create.revenue.CreateRevenuePostUseCase;
import com.lj.application.post.retrieve.list.ListPostsUseCase;
import com.lj.domain.PaginationSearchQuery;
import com.lj.domain.pagination.Pagination;
import com.lj.domain.post.RecurrenceType;
import com.lj.domain.validation.handler.Notification;
import com.lj.infrastructure.api.PostAPI;
import com.lj.infrastructure.post.PostApiPresenter;
import com.lj.infrastructure.post.models.PostsListApiOutput;
import com.lj.infrastructure.post.models.expense.CreateExpensePostApiInput;
import com.lj.infrastructure.post.models.revenue.CreateRevenuePostApiInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.security.Principal;
import java.util.Objects;
import java.util.function.Function;

@RestController
@CrossOrigin(origins = "*")
public class PostController implements PostAPI {

    private final CreateRevenuePostUseCase createRevenuePostUseCase;
    private final CreateExpensePostUseCase createExpensePostUseCase;
    private final ListPostsUseCase listPostsUseCase;

    public PostController(final CreateRevenuePostUseCase createRevenuePostUseCase,
                          final CreateExpensePostUseCase createExpensePostUseCase,
                          final ListPostsUseCase listPostsUseCase) {
        this.createRevenuePostUseCase = Objects.requireNonNull(createRevenuePostUseCase);
        this.listPostsUseCase = Objects.requireNonNull(listPostsUseCase);
        this.createExpensePostUseCase = Objects.requireNonNull(createExpensePostUseCase);
    }

    @Override
    @RolesAllowed("back-user")
    public ResponseEntity<?> createPostRevenue(final Principal principal, final CreateRevenuePostApiInput input) {
        final var aCommand = CreateRevenuePostCommand.with(
                    input.description(),
                    input.category(),
                    input.subcategory(),
                    RecurrenceType.valueOf(input.recurrence()),
                    input.account(),
                    principal.getName(),
                    input.note(),
                    input.effective() != null ? input.effective() : false,
                    input.amount()  != null ? input.amount() : 0l,
                    input.installment()!= null ? input.installment() : 1,
                    input.dueDate()
        );

        final Function<Notification, ResponseEntity<?>> onError =
                ResponseEntity.unprocessableEntity()::body;

        final Function<CreateRevenuePostOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/posts/" + output.id())).body(output);


        return this.createRevenuePostUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    @RolesAllowed("back-user")
    public ResponseEntity<?> createPostExpense(Principal principal, CreateExpensePostApiInput input) {
        final var aCommand = CreateExpensePostCommand.with(
                input.description(),
                input.category(),
                input.subcategory(),
                RecurrenceType.valueOf(input.recurrence()),
                input.account(),
                principal.getName(),
                input.note(),
                input.effective() != null ? input.effective() : false,
                input.amount()  != null ? input.amount() : 0l,
                input.installment()!= null ? input.installment() : 1,
                input.dueDate()
        );

        final Function<Notification, ResponseEntity<?>> onError =
                ResponseEntity.unprocessableEntity()::body;

        final Function<CreateExpensePostOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/posts/" + output.id())).body(output);


        return this.createExpensePostUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    public Pagination<PostsListApiOutput> listPosts(final String search,
                                                    final int page,
                                                    final int perPage,
                                                    final String sort,
                                                    final String dir) {
        return listPostsUseCase.execute(
                new PaginationSearchQuery(page,
                                            perPage,
                                            search,
                                            sort,
                                            dir))
                .map(PostApiPresenter::present);
    }
}
