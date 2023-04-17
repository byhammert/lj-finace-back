package com.lj.application.account.update;

import com.lj.application.account.create.DefaultCreateAccountUseCase;
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

import java.util.Objects;
import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateAccountUseCaseTest {

    @InjectMocks
    private DefaultUpdateAccountUseCase useCase;

    @Mock
    private AccountGateway accountGateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(accountGateway);
    }

    @Test
    public void givenAValidCommand_whenCallsUpdateAccount_thenShouldReturnAccountId() {
        final var expectedName = "Minha Carteira";
        final var expectedUser = "usuarioTeste";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final long expectedBalance = 100;
        final var expectedIsActive = true;
        

        final var anAccount = Account.newAccount(
                "Name Teste", expectedUser, "Categoria Teste", "expectedSymbol", 0, expectedIsActive);

        final var expectedId = anAccount.getId();
        
        final var aCommand = UpdateAccountCommand.with(
                expectedId.getValue(),
                expectedName,
                expectedCategory,
                expectedSymbol,
                expectedBalance,
                expectedIsActive
        );

        when(accountGateway.findById(eq(expectedId))).thenReturn(Optional.of(anAccount.clone()));

        when(accountGateway.update(any())).thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        verify(accountGateway, times(1)).findById(eq(expectedId));

        verify(accountGateway, times(1)).update(argThat(aUpdatedAccount ->
                Objects.equals(expectedName, aUpdatedAccount.getName())
                        && Objects.equals(expectedUser, aUpdatedAccount.getUser())
                        && Objects.equals(expectedCategory, aUpdatedAccount.getCategory())
                        && Objects.equals(expectedSymbol, aUpdatedAccount.getSymbol())
                        && Objects.equals(expectedBalance, aUpdatedAccount.getBalance())
                        && Objects.equals(expectedIsActive, aUpdatedAccount.isActive())
                        && Objects.equals(expectedId, aUpdatedAccount.getId())
                        && Objects.equals(anAccount.getCreatedAt(), aUpdatedAccount.getCreatedAt())
                        && anAccount.getUpdatedAt().isBefore(aUpdatedAccount.getUpdatedAt())
                        && Objects.isNull(aUpdatedAccount.getDeletedAt())

        ));
    }

    @Test
    public void givenAnInvalidName_whenCallsUpdateAccount_thenShouldReturnDomainException() {

        final String expectedName = null;
        final var expectedUser = "usuarioTeste";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final long expectedBalance = 100;
        final var expectedIsActive = true;

        final var anAccount = Account.newAccount(
                "Name Teste", expectedUser, "Categoria Teste", "expectedSymbol", 0, expectedIsActive);

        final var expectedErrorMessage = "'name' should not be null";
        final var expectedErrorCount = 1;

        final var expectedId = anAccount.getId();

        final var aCommand = UpdateAccountCommand
                .with(expectedId.getValue(), expectedName, expectedCategory, expectedSymbol,expectedBalance, expectedIsActive);

        when(accountGateway.findById(eq(expectedId))).thenReturn(Optional.of(anAccount.clone()));

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());

        verify(accountGateway, times(0)).update(any());
    }

    @Test
    public void givenAValidInactivateCommand_whenCallsUpdateAccount_thenShouldReturnInactiveAccountId() {
        final String expectedName = "Minha Carteira";
        final var expectedUser = "usuarioTeste";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final long expectedBalance = 100;
        final var expectedIsActive = false;

        final var anAccount = Account.newAccount(
                "Name Teste", expectedUser, "Categoria Teste", "expectedSymbol", 0, true);

        final var expectedId = anAccount.getId();

        final var aCommand = UpdateAccountCommand.with(
                expectedId.getValue(),
                expectedName,
                expectedCategory,
                expectedSymbol,
                expectedBalance,
                expectedIsActive
        );

        when(accountGateway.findById(eq(expectedId))).thenReturn(Optional.of(anAccount.clone()));

        when(accountGateway.update(any())).thenAnswer(returnsFirstArg());

        Assertions.assertTrue(anAccount.isActive());
        Assertions.assertNull(anAccount.getDeletedAt());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        verify(accountGateway, times(1)).findById(eq(expectedId));

        verify(accountGateway, times(1)).update(argThat(aUpdatedAccount ->
                Objects.equals(expectedName, aUpdatedAccount.getName())
                        && Objects.equals(expectedUser, aUpdatedAccount.getUser())
                        && Objects.equals(expectedCategory, aUpdatedAccount.getCategory())
                        && Objects.equals(expectedSymbol, aUpdatedAccount.getSymbol())
                        && Objects.equals(expectedBalance, aUpdatedAccount.getBalance())
                        && Objects.equals(expectedIsActive, aUpdatedAccount.isActive())
                        && Objects.equals(expectedId, aUpdatedAccount.getId())
                        && Objects.equals(anAccount.getCreatedAt(), aUpdatedAccount.getCreatedAt())
                        && anAccount.getUpdatedAt().isBefore(aUpdatedAccount.getUpdatedAt())
                        && Objects.nonNull(aUpdatedAccount.getDeletedAt())

        ));
    }

    @Test
    public void givenAValidCommand_whenGatewayThrowsRandomException_thenShouldReturnAException() {
        final String expectedName = "Minha Carteira";
        final var expectedUser = "usuarioTeste";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final long expectedBalance = 100;
        final var expectedIsActive = false;
        final var expectedErrorMessage = "Gateway error";
        final var expectedErrorCount = 1;

        final var anAccount = Account.newAccount(
                "Name Teste", expectedUser, "Categoria Teste", "expectedSymbol", 0, true);

        final var expectedId = anAccount.getId();

        final var aCommand = UpdateAccountCommand.with(
                expectedId.getValue(),
                expectedName,
                expectedCategory,
                expectedSymbol,
                expectedBalance,
                expectedIsActive
        );

        when(accountGateway.findById(eq(expectedId))).thenReturn(Optional.of(anAccount.clone()));

        when(accountGateway.update(any())).thenThrow(new IllegalStateException(expectedErrorMessage));

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

        verify(accountGateway, times(1)).update(argThat(aUpdatedAccount ->
                Objects.equals(expectedName, aUpdatedAccount.getName())
                        && Objects.equals(expectedUser, aUpdatedAccount.getUser())
                        && Objects.equals(expectedCategory, aUpdatedAccount.getCategory())
                        && Objects.equals(expectedSymbol, aUpdatedAccount.getSymbol())
                        && Objects.equals(expectedBalance, aUpdatedAccount.getBalance())
                        && Objects.equals(expectedIsActive, aUpdatedAccount.isActive())
                        && Objects.equals(expectedId, aUpdatedAccount.getId())
                        && Objects.equals(anAccount.getCreatedAt(), aUpdatedAccount.getCreatedAt())
                        && anAccount.getUpdatedAt().isBefore(aUpdatedAccount.getUpdatedAt())
                        && Objects.nonNull(aUpdatedAccount.getDeletedAt())

        ));
    }

    @Test
    public void givenACommandInvalidID_whenCallsUpdateAccount_thenShouldReturnNotFoundException() {

        final String expectedName = "Minha Carteira";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final long expectedBalance = 100;
        final var expectedIsActive = false;
        final var expectedId = "123";
        final var expectedErrorMessage = "Account with ID %s was not found".formatted(expectedId);

        final var aCommand = UpdateAccountCommand.with(
                expectedId,
                expectedName,
                expectedCategory,
                expectedSymbol,
                expectedBalance,
                expectedIsActive
        );

        when(accountGateway.findById(eq(AccountID.from(expectedId)))).thenReturn(Optional.empty());

        final var actualException = Assertions.assertThrows(NotFoundException.class, () -> useCase.execute(aCommand));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());

        verify(accountGateway, times(1)).findById(eq(AccountID.from(expectedId)));

        verify(accountGateway, times(0)).update(any());
    }
}
