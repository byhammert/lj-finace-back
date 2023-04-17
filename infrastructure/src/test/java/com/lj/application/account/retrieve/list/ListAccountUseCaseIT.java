package com.lj.application.account.retrieve.list;

import com.lj.IntegrationTest;
import com.lj.domain.account.Account;
import com.lj.infrastructure.account.persistence.AccountJpaEntity;
import com.lj.infrastructure.account.persistence.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

@IntegrationTest
public class ListAccountUseCaseIT {

    @Autowired
    private ListAccountsUseCase useCase;

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    void mockUp() {
        final var accounts = Stream.of(
                    Account.newAccount("Nome", "User", "Categoria", "Symbol", 0, true),
                    Account.newAccount("Nome2", "User", "Categoria", "Symbol2", 0, true),
                    Account.newAccount("Nome", "User2", "Categoria", "Symbol", 0, true)

                )
                .map(AccountJpaEntity::from)
                .toList();

        accountRepository.saveAllAndFlush(accounts);
    }

    @Test
    public void givenAValidUser_whenHasNoResults_thenShouldReturnEmptyAccounts() {
        final var actualResult = useCase.execute("User3");

        Assertions.assertEquals(0, actualResult.size());
    }

    @Test
    public void givenAValidUser_whenHasListAccounts_thenShouldReturnAccounts() {
        final var actualResult = useCase.execute("User2");

        Assertions.assertEquals(1, actualResult.size());
    }


}
