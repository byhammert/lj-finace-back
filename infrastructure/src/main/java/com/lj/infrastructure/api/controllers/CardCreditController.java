package com.lj.infrastructure.api.controllers;

import com.lj.application.creditcard.create.CreateCreditCardCommand;
import com.lj.application.creditcard.create.CreateCreditCardOutput;
import com.lj.application.creditcard.create.CreateCreditCardUseCase;
import com.lj.application.post.create.revenue.CreateRevenuePostCommand;
import com.lj.application.post.create.revenue.CreateRevenuePostOutput;
import com.lj.domain.post.RecurrenceType;
import com.lj.domain.validation.handler.Notification;
import com.lj.infrastructure.api.CreditCardAPI;
import com.lj.infrastructure.creditcard.models.CreateCreditCardApiInput;
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
public class CardCreditController implements CreditCardAPI {

    private final CreateCreditCardUseCase createCreditCardUseCase;

    public CardCreditController(final CreateCreditCardUseCase createCreditCardUseCase) {
        this.createCreditCardUseCase = Objects.requireNonNull(createCreditCardUseCase);
    }

    @Override
    @RolesAllowed("back-user")
    public ResponseEntity<?> createCreditCard(Principal principal, CreateCreditCardApiInput input) {
        final var aCommand = CreateCreditCardCommand.with(
                input.description(),
                input.account(),
                principal.getName(),
                input.closingDay(),
                input.dueDay(),
                input.limit()
        );

        final Function<Notification, ResponseEntity<?>> onError =
                ResponseEntity.unprocessableEntity()::body;

        final Function<CreateCreditCardOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/credit-cards/" + output.id())).body(output);


        return this.createCreditCardUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }



}
