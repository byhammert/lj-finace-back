package com.lj.application.account.retrieve.get;

import com.lj.IntegrationTest;
import com.lj.application.account.delete.DeleteAccountUseCase;
import com.lj.domain.account.Account;
import com.lj.domain.account.AccountGateway;
import com.lj.domain.account.AccountID;
import com.lj.domain.exceptions.DomainException;
import com.lj.domain.exceptions.NotFoundException;
import com.lj.infrastructure.account.persistence.AccountJpaEntity;
import com.lj.infrastructure.account.persistence.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;

@IntegrationTest
public class GetAccountByIdUseCaseIT {

    @Autowired
    private GetAccountByIdUseCase useCase;

    @SpyBean
    private AccountGateway accountGateway;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void givenAValidId_whenCallsGetAccount_thenShouldReturnAccount() {
        final var expectedName = "Minha Carteira";
        final var expectedUser = "usuarioTeste";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final var expectedBalance = 0;
        final var expectedIsActive = true;

        final var anAccount = Account.newAccount(expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);
        final var expectedId = anAccount.getId();

        save(anAccount);

        final var actualAccount = useCase.execute(expectedId.getValue());
        Assertions.assertEquals(expectedName, actualAccount.name());
        Assertions.assertEquals(expectedUser, actualAccount.user());
        Assertions.assertEquals(expectedCategory, actualAccount.category());
        Assertions.assertEquals(expectedSymbol, actualAccount.symbol());
        Assertions.assertEquals(expectedBalance, actualAccount.balance());
        Assertions.assertEquals(expectedIsActive, actualAccount.isActive());
        Assertions.assertNotNull(actualAccount.createdAt());
        Assertions.assertNotNull(actualAccount.updatedAt());
        Assertions.assertNull(actualAccount.deletedAt());
    }

    @Test
    public void givenAInvalidId_whenCallsGetAccount_thenShouldReturnNotFound() {
        final var expectedId = AccountID.from("123");
        final var expectedErrorMessage = "Account with ID %s was not found".formatted(expectedId.getValue());


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

        doThrow(new IllegalStateException(expectedErrorMessage))
                .when(accountGateway).findById(eq(expectedId));

        final var actualException = Assertions.assertThrows(
                IllegalStateException.class,
                () -> useCase.execute(expectedId.getValue())
        );

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
