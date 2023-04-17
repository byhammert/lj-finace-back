package com.lj.application.account.delete;

import com.lj.IntegrationTest;
import com.lj.application.account.create.CreateAccountUseCase;
import com.lj.domain.account.Account;
import com.lj.domain.account.AccountGateway;
import com.lj.domain.account.AccountID;
import com.lj.infrastructure.account.persistence.AccountJpaEntity;
import com.lj.infrastructure.account.persistence.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@IntegrationTest
public class DeleteAccountUseCaseIT {

    @Autowired
    private AccountRepository accountRepository;

    @SpyBean
    private AccountGateway accountGateway;

    @Autowired
    private DeleteAccountUseCase useCase;

    @Test
    public void givenAValidId_whenCallsDeleteAccount_thenShouldBeOk() {
        final var anAccount = Account.newAccount("Nome", "User", "Categoria", "Symbol", 0, true);
        final var expectedId = anAccount.getId();

        save(anAccount);
        Assertions.assertEquals(1, accountRepository.count());
        useCase.execute(expectedId.getValue());
        Assertions.assertEquals(0, accountRepository.count());
    }

    @Test
    public void givenAInvalidId_whenCallsDeleteAccount_thenShouldBeOk() {
        final var expectedId = AccountID.from("123");

        Assertions.assertEquals(0, accountRepository.count());
        useCase.execute(expectedId.getValue());
        Assertions.assertEquals(0, accountRepository.count());
    }

    @Test
    public void givenAValidId_whenGatewayThrowsException_thenShouldReturnException() {
        final var anAccount = Account.newAccount("Nome", "User", "Categoria", "Symbol", 0, true);
        final var expectedId = anAccount.getId();

        doThrow(new IllegalStateException("Gateway error"))
                .when(accountGateway).deleteById(eq(expectedId));

        Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(expectedId.getValue()));

        verify(accountGateway, Mockito.times(1)).deleteById(eq(expectedId));
    }

    private void save(final Account... anAccount) {
        accountRepository.saveAllAndFlush(
                Arrays.stream(anAccount)
                        .map(AccountJpaEntity::from)
                        .toList()
        );
    }
}
