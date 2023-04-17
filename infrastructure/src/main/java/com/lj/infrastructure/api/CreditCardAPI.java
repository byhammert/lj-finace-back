package com.lj.infrastructure.api;

import com.lj.infrastructure.creditcard.models.CreateCreditCardApiInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping(value = "/credit-cards")
@Tag(name = "CreditCard")
public interface CreditCardAPI {

    @PostMapping()
    @Operation(summary = "Create a new credit card")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Credit Card created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown")
    })
    ResponseEntity<?> createCreditCard(Principal principal, @RequestBody CreateCreditCardApiInput input);

}
