package com.lj.application.account.create;

import com.lj.domain.account.AccountGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateInvoiceUseCaseTest {

    @InjectMocks
    private DefaultCreateAccountUseCase useCase;

    @Mock
    private AccountGateway accountGateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(accountGateway);
    }

    @Test
    public void givenAValidCommand_whenCallsCreateAccount_thenShouldReturnAccountId() {
        final var expectedName = "Minha Carteira";
        final var expectedUser = "usuarioTeste";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final long expectedBalance = 0L;
        final var expectedIsActive = true;

        final var aCommand =
                CreateAccountCommand.with(expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        when(accountGateway.create(any())).thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        verify(accountGateway, times(1)).create(argThat(anAccount ->
                Objects.equals(expectedName, anAccount.getName())
                        && Objects.equals(expectedUser, anAccount.getUser())
                        && Objects.equals(expectedCategory, anAccount.getCategory())
                        && Objects.equals(expectedSymbol, anAccount.getSymbol())
                        && Objects.equals(expectedBalance, anAccount.getBalance())
                        && Objects.equals(expectedIsActive, anAccount.isActive())
                        && Objects.nonNull(anAccount.getId())
                        && Objects.nonNull(anAccount.getCreatedAt())
                        && Objects.nonNull(anAccount.getUpdatedAt())
                        && Objects.isNull(anAccount.getDeletedAt())

        ));
    }

    @Test
    public void givenAnInvalidName_whenCallsCreateAccount_thenShouldReturnDomainException() {
        final String expectedName = null;
        final var expectedUser = "usuarioTeste";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final long expectedBalance = 0;
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedErrorCount = 1;

        final var aCommand =
                CreateAccountCommand.with(expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);


        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());

        verify(accountGateway, times(0)).create(any());
    }

    @Test
    public void givenAValidCommandWithInactiveAccount_whenCallsCreateAccount_thenShouldReturnInactiveAccountId() {
        final var expectedName = "Minha Carteira";
        final var expectedUser = "usuarioTeste";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final long expectedBalance = 0;
        final var expectedIsActive = false;

        final var aCommand =
                CreateAccountCommand.with(expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        when(accountGateway.create(any())).thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        verify(accountGateway, times(1)).create(argThat(anAccount ->
                Objects.equals(expectedName, anAccount.getName())
                        && Objects.equals(expectedUser, anAccount.getUser())
                        && Objects.equals(expectedCategory, anAccount.getCategory())
                        && Objects.equals(expectedSymbol, anAccount.getSymbol())
                        && Objects.equals(expectedBalance, anAccount.getBalance())
                        && Objects.equals(expectedIsActive, anAccount.isActive())
                        && Objects.nonNull(anAccount.getId())
                        && Objects.nonNull(anAccount.getCreatedAt())
                        && Objects.nonNull(anAccount.getUpdatedAt())
                        && Objects.nonNull(anAccount.getDeletedAt())

        ));
    }

    @Test
    public void givenAValidCommand_whenGatewayThrowsRandomException_thenShouldReturnAException() {

        final var expectedName = "Minha Carteira";
        final var expectedUser = "usuarioTeste";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final long expectedBalance = 0;
        final var expectedIsActive = true;
        final var expectedErrorMessage = "Gateway error";
        final var expectedErrorCount = 1;

        final var aCommand =
                CreateAccountCommand.with(expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        when(accountGateway.create(any())).thenThrow(new IllegalStateException(expectedErrorMessage));

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());

        verify(accountGateway, times(1)).create(argThat(anAccount ->
                Objects.equals(expectedName, anAccount.getName())
                        && Objects.equals(expectedUser, anAccount.getUser())
                        && Objects.equals(expectedCategory, anAccount.getCategory())
                        && Objects.equals(expectedSymbol, anAccount.getSymbol())
                        && Objects.equals(expectedBalance, anAccount.getBalance())
                        && Objects.equals(expectedIsActive, anAccount.isActive())
                        && Objects.nonNull(anAccount.getId())
                        && Objects.nonNull(anAccount.getCreatedAt())
                        && Objects.nonNull(anAccount.getUpdatedAt())
                        && Objects.isNull(anAccount.getDeletedAt())

        ));
    }

}
