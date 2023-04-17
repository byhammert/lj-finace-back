package com.lj.application.account.retrieve.get;

import com.lj.domain.account.Account;
import com.lj.domain.account.AccountGateway;
import com.lj.domain.account.AccountID;
import com.lj.domain.exceptions.DomainException;
import com.lj.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAccountByIdUseCaseTest {

    @InjectMocks
    private DefaultGetAccountByIdUseCase useCase;

    @Mock
    private AccountGateway accountGateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(accountGateway);
    }

    @Test
    public void givenAValidId_whenCallsGetAccount_thenShouldReturnAccount() {
        final var expectedName = "Minha Carteira";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final long expectedBalance = 0;
        final var expectedIsActive = true;
        final var expectedUser = "usuarioTeste";

        final var anAccount = Account.newAccount(
                expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        final var expectedId = anAccount.getId();

        when(accountGateway.findById(eq(expectedId))).thenReturn(Optional.of(anAccount.clone()));

        final var actualAccount = useCase.execute(expectedId.getValue());


        Assertions.assertEquals(expectedId, actualAccount.id());
        Assertions.assertEquals(expectedName, actualAccount.name());
        Assertions.assertEquals(expectedCategory, actualAccount.category());
        Assertions.assertEquals(expectedSymbol, actualAccount.symbol());
        Assertions.assertEquals(expectedBalance, actualAccount.balance());
        Assertions.assertEquals(expectedUser, actualAccount.user());
        Assertions.assertEquals(expectedIsActive, actualAccount.isActive());
        Assertions.assertEquals(anAccount.getCreatedAt(), actualAccount.createdAt());
        Assertions.assertEquals(anAccount.getUpdatedAt(), actualAccount.updatedAt());
        Assertions.assertEquals(anAccount.getDeletedAt(), actualAccount.deletedAt());

        verify(accountGateway, Mockito.times(1)).findById(eq(expectedId));
    }

    @Test
    public void givenAInvalidId_whenCallsGetAccount_thenShouldReturnNotFound() {
        final var expectedId = AccountID.from("123");
        final var expectedErrorMessage = "Account with ID %s was not found".formatted(expectedId.getValue());

        when(accountGateway.findById(eq(expectedId))).thenReturn(Optional.empty());

        final var actualException = Assertions.assertThrows(
                NotFoundException.class,
                () -> useCase.execute(expectedId.getValue())
        );

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    public void givenAValidId_whenGatewayThrowsException_thenShouldReturnException() {
        final var expectedId = AccountID.from("123");
        final var expectedErrorMessage = "Gateway error";

        when(accountGateway.findById(eq(expectedId))).thenThrow(new IllegalStateException(expectedErrorMessage));

        final var actualException = Assertions.assertThrows(
                IllegalStateException.class,
                () -> useCase.execute(expectedId.getValue())
        );

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}
