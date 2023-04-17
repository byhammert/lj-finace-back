package com.lj.application.account.retrieve.list;

import com.lj.application.account.delete.DefaultDeleteAccountUseCase;
import com.lj.domain.account.Account;
import com.lj.domain.account.AccountGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListAccountsUseCaseTest {

    @InjectMocks
    private DefaultListAccountsUseCase useCase;

    @Mock
    private AccountGateway accountGateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(accountGateway);
    }

    @Test
    public void givenAValidUser_whenCallsListAccounts_thenShouldReturnAccounts() {
        final var accounts = List.of(
       Account.newAccount(
                "teste nome", "teste user", "teste categoria", "teste symbol", 0, true),

        Account.newAccount(
                "teste nome2", "teste user", "teste categoria2", "teste symbol2", 100, true)

        );


        final var expectedList = accounts;

        final var expectedItemsCount = 2;
        final var expectedResult = expectedList.stream().map(AccountListOutput::from).toList();

        when(accountGateway.findAllByUserOrderByNameAsc(eq("teste user"))).thenReturn(expectedList);

        final var actualResult = useCase.execute("teste user");

        Assertions.assertEquals(expectedItemsCount, actualResult.size());
        Assertions.assertEquals(actualResult, expectedResult);

    }

    @Test
    public void givenAValidUser_whenHasNoResults_thenShouldReturnEmptyAccounts() {
        final var accounts = List.<Account>of();

        when(accountGateway.findAllByUserOrderByNameAsc(eq("teste user"))).thenReturn(List.of());

        final var actualResult = useCase.execute("teste user");

        Assertions.assertEquals(0, actualResult.size());
    }

    @Test
    public void givenAValidQuery_whenGatewayThrowsException_thenShouldReturnException() {

        final var expectedErrorMessage = "Gateway error";

        when(accountGateway.findAllByUserOrderByNameAsc(eq("teste user"))).thenThrow(new IllegalStateException(expectedErrorMessage));

        final var actualException =
                Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute("teste user"));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}
