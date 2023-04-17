package com.lj.infrastructure.api.controllers;

import com.lj.application.account.create.CreateAccountCommand;
import com.lj.application.account.create.CreateAccountOutput;
import com.lj.application.account.create.CreateAccountUseCase;
import com.lj.application.account.delete.DeleteAccountUseCase;
import com.lj.application.account.retrieve.get.GetAccountByIdUseCase;
import com.lj.application.account.retrieve.list.ListAccountsUseCase;
import com.lj.application.account.update.UpdateAccountCommand;
import com.lj.application.account.update.UpdateAccountOutput;
import com.lj.application.account.update.UpdateAccountUseCase;
import com.lj.domain.validation.handler.Notification;
import com.lj.infrastructure.account.AccountApiPresenter;
import com.lj.infrastructure.account.AccountListApiPresenter;
import com.lj.infrastructure.account.models.AccountApiOutput;
import com.lj.infrastructure.account.models.AccountListApiOutput;
import com.lj.infrastructure.account.models.CreateAccountApiInput;
import com.lj.infrastructure.account.models.UpdateAccountApiInput;
import com.lj.infrastructure.api.AccountAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@RestController
@CrossOrigin(origins = "*")
public class AccountController implements AccountAPI {

    private final CreateAccountUseCase createAccountUseCase;
    private final GetAccountByIdUseCase getAccountByIdUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;
    private final DeleteAccountUseCase deleteAccountUseCase;
    private final ListAccountsUseCase listAccountsUseCase;

    public AccountController(final CreateAccountUseCase createAccountUseCase,
                             final GetAccountByIdUseCase getAccountByIdUseCase,
                             final UpdateAccountUseCase updateAccountUseCase,
                             final DeleteAccountUseCase deleteAccountUseCase,
                             final ListAccountsUseCase listAccountsUseCase) {
        this.createAccountUseCase = Objects.requireNonNull(createAccountUseCase);
        this.getAccountByIdUseCase = Objects.requireNonNull(getAccountByIdUseCase);
        this.updateAccountUseCase = Objects.requireNonNull(updateAccountUseCase);
        this.deleteAccountUseCase = Objects.requireNonNull(deleteAccountUseCase);
        this.listAccountsUseCase = Objects.requireNonNull(listAccountsUseCase);
    }

    @Override
    @RolesAllowed("back-user")
    public ResponseEntity<?> createAccount(final Principal principal, final CreateAccountApiInput input) {
        final var aCommand = CreateAccountCommand.with(
                input.name(),
                principal.getName(),
                input.category(),
                input.symbol(),
                input.balance() != null ? input.balance() : 0l,
                input.isActive() != null ? input.isActive() : true
        );

        final Function<Notification, ResponseEntity<?>> onError =
                ResponseEntity.unprocessableEntity()::body;

        final Function<CreateAccountOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/accounts/" + output.id())).body(output);


        return this.createAccountUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    @RolesAllowed("back-user")
    public List<AccountListApiOutput> listAccounts(final Principal principal) {
        return this.listAccountsUseCase.execute(principal.getName()).stream()
                .map(AccountListApiPresenter::present)
                .toList();
    }

    @Override
    @RolesAllowed("back-user")
    public AccountApiOutput getById(final String id) {
        return AccountApiPresenter.present.apply(getAccountByIdUseCase.execute(id));
    }

    @Override
    @RolesAllowed("back-user")
    public ResponseEntity<?> updateAccount(final String id, final UpdateAccountApiInput input) {
        final var aCommand = UpdateAccountCommand.with(
                id,
                input.name(),
                input.category(),
                input.symbol(),
                input.balance() != null ? input.balance() : 0l,
                input.isActive() != null ? input.isActive() : true
        );

        final Function<Notification, ResponseEntity<?>> onError =
                ResponseEntity.unprocessableEntity()::body;

        final Function<UpdateAccountOutput, ResponseEntity<?>> onSuccess = ResponseEntity::ok;


        return this.updateAccountUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    @RolesAllowed("back-user")
    public void deleteAccount(final String anId) {
        this.deleteAccountUseCase.execute(anId);
    }
}
