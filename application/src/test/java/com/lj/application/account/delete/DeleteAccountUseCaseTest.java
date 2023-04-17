package com.lj.application.account.delete;

import com.lj.domain.account.Account;
import com.lj.domain.account.AccountGateway;
import com.lj.domain.account.AccountID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeleteAccountUseCaseTest {

    @InjectMocks
    private DefaultDeleteAccountUseCase useCase;

    @Mock
    private AccountGateway accountGateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(accountGateway);
    }

    @Test
    public void givenAValidId_whenCallsDeleteAccount_thenShouldBeOk() {
        final var anAccount = Account.newAccount("teste", "teste", "teste", "teste",0,true);
        final var expectedId = anAccount.getId();

        doNothing().when(accountGateway).deleteById(eq(expectedId));

        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        verify(accountGateway, Mockito.times(1)).deleteById(eq(expectedId));
    }

    @Test
    public void givenAInvalidId_whenCallsDeleteAccount_thenShouldBeOk() {
        final var expectedId = AccountID.from("123");

        doNothing().when(accountGateway).deleteById(eq(expectedId));

        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        verify(accountGateway, Mockito.times(1)).deleteById(eq(expectedId));
    }

    @Test
    public void givenAValidId_whenGatewayThrowsException_thenShouldReturnException() {
        final var anAccount = Account.newAccount("teste", "teste", "teste", "teste",0,true);
        final var expectedId = anAccount.getId();

        doThrow(new IllegalStateException("Gateway error"))
                .when(accountGateway).deleteById(eq(expectedId));

        Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(expectedId.getValue()));

        verify(accountGateway, Mockito.times(1)).deleteById(eq(expectedId));
    }
}
