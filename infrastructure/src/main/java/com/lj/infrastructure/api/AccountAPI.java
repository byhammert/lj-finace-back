package com.lj.infrastructure.api;

import com.lj.infrastructure.account.models.AccountApiOutput;
import com.lj.infrastructure.account.models.AccountListApiOutput;
import com.lj.infrastructure.account.models.CreateAccountApiInput;
import com.lj.infrastructure.account.models.UpdateAccountApiInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.List;

@RequestMapping(value = "/accounts")
@Tag(name = "Accounts")
public interface AccountAPI {

    @PostMapping()
    @Operation(summary = "Create a new account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown")
    })
    ResponseEntity<?> createAccount(Principal principal, @RequestBody CreateAccountApiInput input);

    @GetMapping()
    @Operation(summary = "List all accounts of user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed successfully"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown")
    })
    List<AccountListApiOutput> listAccounts(Principal principal);

    @GetMapping(
            value = "{id}"
    )
    @Operation(summary = "Get an account by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Account was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown")
    })
    AccountApiOutput getById(@PathVariable(name = "id") String id);

    @PutMapping(
            value = "{id}"
    )
    @Operation(summary = "Updated an account by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account updated successfully"),
            @ApiResponse(responseCode = "404", description = "Account was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown")
    })
    ResponseEntity<?> updateAccount(@PathVariable(name = "id") String id, @RequestBody UpdateAccountApiInput input);

    @DeleteMapping(
            value = "{id}"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete an account by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Account deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Account was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown")
    })
    void deleteAccount(@PathVariable(name = "id") String id);
}
