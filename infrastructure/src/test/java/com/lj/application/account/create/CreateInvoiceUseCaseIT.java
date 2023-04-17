package com.lj.application.account.create;

import com.lj.IntegrationTest;
import com.lj.domain.account.AccountGateway;
import com.lj.infrastructure.account.persistence.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@IntegrationTest
public class CreateInvoiceUseCaseIT {

    @Autowired
    private CreateAccountUseCase useCase;

    @Autowired
    private AccountRepository accountRepository;

    @SpyBean
    private AccountGateway accountGateway;

    @Test
    public void givenAValidCommand_whenCallsCreateAccount_thenShouldReturnAccountId() {

        final var expectedName = "Minha Carteira";
        final var expectedUser = "usuarioTeste";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final var expectedBalance = 0;
        final var expectedIsActive = true;

        Assertions.assertEquals(0, accountRepository.count());

        final var aCommand = CreateAccountCommand
                .with(expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);


        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertEquals(1, accountRepository.count());

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        final var actualAccount = accountRepository.findById(actualOutput.id()).get();
        Assertions.assertEquals(expectedName, actualAccount.getName());
        Assertions.assertEquals(expectedUser, actualAccount.getUser());
        Assertions.assertEquals(expectedCategory, actualAccount.getCategory());
        Assertions.assertEquals(expectedSymbol, actualAccount.getSymbol());
        Assertions.assertEquals(expectedBalance, actualAccount.getBalance());
        Assertions.assertEquals(expectedIsActive, actualAccount.isActive());
        Assertions.assertNotNull(actualAccount.getCreatedAt());
        Assertions.assertNotNull(actualAccount.getUpdatedAt());
        Assertions.assertNull(actualAccount.getDeletedAt());
    }

    @Test
    public void givenAnInvalidName_whenCallsCreateAccount_thenShouldReturnDomainException() {
        final String expectedName = null;
        final var expectedIsActive = true;
        final var expectedUser = "usuarioTeste";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final var expectedBalance = 0;
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedErrorCount = 1;

        Assertions.assertEquals(0, accountRepository.count());

        final var aCommand = CreateAccountCommand
                .with(expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());

        Assertions.assertEquals(0, accountRepository.count());

        verify(accountGateway, times(0)).create(any());
    }

    @Test
    public void givenAValidCommandWithInactiveAccount_whenCallsCreateAccount_thenShouldReturnInactiveAccountId() {
        final var expectedName = "Minha Carteira";
        final var expectedUser = "usuarioTeste";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final var expectedBalance = 0;
        final var expectedIsActive = false;

        Assertions.assertEquals(0, accountRepository.count());

        final var aCommand = CreateAccountCommand
                .with(expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        Assertions.assertEquals(0, accountRepository.count());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Assertions.assertEquals(1, accountRepository.count());

        final var actualAccount = accountRepository.findById(actualOutput.id()).get();
        Assertions.assertEquals(expectedName, actualAccount.getName());
        Assertions.assertEquals(expectedUser, actualAccount.getUser());
        Assertions.assertEquals(expectedCategory, actualAccount.getCategory());
        Assertions.assertEquals(expectedSymbol, actualAccount.getSymbol());
        Assertions.assertEquals(expectedBalance, actualAccount.getBalance());
        Assertions.assertEquals(expectedIsActive, actualAccount.isActive());
        Assertions.assertNotNull(actualAccount.getCreatedAt());
        Assertions.assertNotNull(actualAccount.getUpdatedAt());
        Assertions.assertNotNull(actualAccount.getDeletedAt());

    }

    @Test
    public void givenAValidCommand_whenGatewayThrowsRandomException_thenShouldReturnAException() {
        final var expectedName = "Minha Carteira";
        final var expectedUser = "usuarioTeste";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final var expectedBalance = 0;
        final var expectedIsActive = false;
        final var expectedErrorMessage = "Gateway error";
        final var expectedErrorCount = 1;

        Assertions.assertEquals(0, accountRepository.count());

        final var aCommand = CreateAccountCommand
                .with(expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        doThrow(new IllegalStateException(expectedErrorMessage)).when(accountGateway).create(any());

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());

    }

}
