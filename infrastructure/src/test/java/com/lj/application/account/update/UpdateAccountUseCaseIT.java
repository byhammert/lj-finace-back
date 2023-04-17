package com.lj.application.account.update;

import com.lj.IntegrationTest;
import com.lj.application.account.delete.DeleteAccountUseCase;
import com.lj.domain.account.Account;
import com.lj.domain.account.AccountGateway;
import com.lj.domain.exceptions.DomainException;
import com.lj.domain.exceptions.NotFoundException;
import com.lj.infrastructure.account.persistence.AccountJpaEntity;
import com.lj.infrastructure.account.persistence.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@IntegrationTest
public class UpdateAccountUseCaseIT {

    @Autowired
    private AccountRepository accountRepository;

    @SpyBean
    private AccountGateway accountGateway;

    @Autowired
    private UpdateAccountUseCase useCase;

    @Test
    public void givenAValidCommand_whenCallsUpdateAccount_thenShouldReturnAccountId() {
        final var anAccount = Account.newAccount("Nome", "User", "Categoria", "Symbol", 0, true);
        final var expectedId = anAccount.getId();

        save(anAccount);

        final var expectedName = "Minha Carteira";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final var expectedBalance = 0;
        final var expectedIsActive = true;

        final var aCommand = UpdateAccountCommand.with(
                expectedId.getValue(),
                expectedName,
                expectedCategory,
                expectedSymbol,
                expectedBalance,
                expectedIsActive
        );

        Assertions.assertEquals(1, accountRepository.count());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertEquals(1, accountRepository.count());

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        final var actualAccount = accountRepository.findById(expectedId.getValue()).get();
        Assertions.assertEquals(expectedName, actualAccount.getName());
        Assertions.assertEquals("User", actualAccount.getUser());
        Assertions.assertEquals(expectedCategory, actualAccount.getCategory());
        Assertions.assertEquals(expectedSymbol, actualAccount.getSymbol());
        Assertions.assertEquals(expectedBalance, actualAccount.getBalance());
        Assertions.assertEquals(expectedIsActive, actualAccount.isActive());
        Assertions.assertNotNull(actualAccount.getCreatedAt());
        Assertions.assertTrue(anAccount.getUpdatedAt().isBefore(actualAccount.getUpdatedAt()));
        Assertions.assertNull(actualAccount.getDeletedAt());
    }

    @Test
    public void givenAnInvalidName_whenCallsUpdateAccount_thenShouldReturnDomainException() {
        final var anAccount = Account.newAccount("Nome", "User", "Categoria", "Symbol", 0, true);
        final var expectedId = anAccount.getId();
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedErrorCount = 1;

        save(anAccount);

        final String expectedName = null;
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final var expectedBalance = 0;
        final var expectedIsActive = true;

        final var aCommand = UpdateAccountCommand.with(
                expectedId.getValue(),
                expectedName,
                expectedCategory,
                expectedSymbol,
                expectedBalance,
                expectedIsActive
        );


        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());

        verify(accountGateway, times(0)).update(any());
    }

    @Test
    public void givenAValidInactivateCommand_whenCallsUpdateAccount_thenShouldReturnInactiveAccountId() {
        final var anAccount = Account.newAccount("Nome", "User", "Categoria", "Symbol", 0, true);
        final var expectedId = anAccount.getId();

        save(anAccount);

        final String expectedName = "Minha Carteira";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final var expectedBalance = 0;
        final var expectedIsActive = false;

        final var aCommand = UpdateAccountCommand.with(
                expectedId.getValue(),
                expectedName,
                expectedCategory,
                expectedSymbol,
                expectedBalance,
                expectedIsActive
        );

        Assertions.assertTrue(anAccount.isActive());
        Assertions.assertNull(anAccount.getDeletedAt());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        final var actualAccount = accountRepository.findById(expectedId.getValue()).get();
        Assertions.assertEquals(expectedName, actualAccount.getName());
        Assertions.assertEquals("User", actualAccount.getUser());
        Assertions.assertEquals(expectedCategory, actualAccount.getCategory());
        Assertions.assertEquals(expectedSymbol, actualAccount.getSymbol());
        Assertions.assertEquals(expectedBalance, actualAccount.getBalance());
        Assertions.assertEquals(expectedIsActive, actualAccount.isActive());
        Assertions.assertNotNull(actualAccount.getCreatedAt());
        Assertions.assertTrue(anAccount.getUpdatedAt().isBefore(actualAccount.getUpdatedAt()));
        Assertions.assertNotNull(actualAccount.getDeletedAt());
    }

    @Test
    public void givenAValidCommand_whenGatewayThrowsRandomException_thenShouldReturnAException() {
        final var anAccount = Account.newAccount("Nome", "User", "Categoria", "Symbol", 0, true);
        final var expectedId = anAccount.getId();

        save(anAccount);

        final String expectedName = "Minha Carteira";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final var expectedBalance = 0;
        final var expectedIsActive = true;
        final var expectedErrorMessage = "Gateway error";
        final var expectedErrorCount = 1;

        final var aCommand = UpdateAccountCommand.with(
                expectedId.getValue(),
                expectedName,
                expectedCategory,
                expectedSymbol,
                expectedBalance,
                expectedIsActive
        );

        doThrow(new IllegalStateException(expectedErrorMessage))
                .when(accountGateway).update(any());

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

        final var actualAccount = accountRepository.findById(expectedId.getValue()).get();
        Assertions.assertEquals(anAccount.getName(), actualAccount.getName());
        Assertions.assertEquals(anAccount.getUser(), actualAccount.getUser());
        Assertions.assertEquals(anAccount.getCategory(), actualAccount.getCategory());
        Assertions.assertEquals(anAccount.getSymbol(), actualAccount.getSymbol());
        Assertions.assertEquals(anAccount.getBalance(), actualAccount.getBalance());
        Assertions.assertEquals(anAccount.isActive(), actualAccount.isActive());
        Assertions.assertNotNull(actualAccount.getCreatedAt());
        Assertions.assertNull(actualAccount.getDeletedAt());
    }

    @Test
    public void givenACommandInvalidID_whenCallsUpdateAccount_thenShouldReturnNotFoundException() {
        final var expectedId = "123";
        final var expectedName = "Minha Carteira";
        final String expectedCategory = null;
        final var expectedSymbol = "NUBANK";
        final var expectedBalance = 0;
        final var expectedIsActive = true;
        final var expectedErrorMessage = "Account with ID %s was not found".formatted(expectedId);

        final var aCommand = UpdateAccountCommand.with(
                expectedId,
                expectedName,
                expectedCategory,
                expectedSymbol,
                expectedBalance,
                expectedIsActive
        );

        final var actualException =
                Assertions.assertThrows(NotFoundException.class, () -> useCase.execute(aCommand));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());

    }

    private void save(final Account... anAccount) {
        accountRepository.saveAllAndFlush(
                Arrays.stream(anAccount)
                        .map(AccountJpaEntity::from)
                        .toList()
        );
    }
}
