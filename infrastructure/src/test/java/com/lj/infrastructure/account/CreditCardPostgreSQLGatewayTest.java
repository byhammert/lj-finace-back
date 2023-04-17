package com.lj.infrastructure.account;

import com.lj.PostgreSQLGatewayTest;
import com.lj.domain.account.Account;
import com.lj.domain.account.AccountID;
import com.lj.infrastructure.account.persistence.AccountJpaEntity;
import com.lj.infrastructure.account.persistence.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@PostgreSQLGatewayTest
public class CreditCardPostgreSQLGatewayTest {

    @Autowired
    private AccountPostgreSQLGateway accountPostgreSQLGateway;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void givenAValidAccount_whenCallsCreate_shouldReturnANewAccount() {
        final var expectedName = "Minha Carteira";
        final var expectedUser = "usuarioTeste";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final var expectedBalance = 0;
        final var expectedIsActive = true;

        final var anAccount = Account.newAccount(
                expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        Assertions.assertEquals(0, accountRepository.count());

        final var actualAccount = accountPostgreSQLGateway.create(anAccount);

        Assertions.assertEquals(1, accountRepository.count());

        Assertions.assertEquals(anAccount.getId(), actualAccount.getId());
        Assertions.assertEquals(expectedName, actualAccount.getName());
        Assertions.assertEquals(expectedUser, actualAccount.getUser());
        Assertions.assertEquals(expectedCategory, actualAccount.getCategory());
        Assertions.assertEquals(expectedSymbol, actualAccount.getSymbol());
        Assertions.assertEquals(expectedBalance, actualAccount.getBalance());
        Assertions.assertEquals(expectedIsActive, actualAccount.isActive());
        Assertions.assertEquals(anAccount.getCreatedAt(), actualAccount.getCreatedAt());
        Assertions.assertEquals(anAccount.getUpdatedAt(), actualAccount.getUpdatedAt());
        Assertions.assertEquals(anAccount.getDeletedAt(), actualAccount.getDeletedAt());
        Assertions.assertNull(anAccount.getDeletedAt());

        final var actualEntity = accountRepository.findById(anAccount.getId().getValue()).get();
        Assertions.assertEquals(anAccount.getId().getValue(), actualEntity.getId());
        Assertions.assertEquals(expectedName, actualEntity.getName());
        Assertions.assertEquals(expectedUser, actualAccount.getUser());
        Assertions.assertEquals(expectedCategory, actualAccount.getCategory());
        Assertions.assertEquals(expectedSymbol, actualAccount.getSymbol());
        Assertions.assertEquals(expectedBalance, actualAccount.getBalance());
        Assertions.assertEquals(expectedIsActive, actualEntity.isActive());
        Assertions.assertEquals(anAccount.getCreatedAt(), actualEntity.getCreatedAt());
        Assertions.assertEquals(anAccount.getUpdatedAt(), actualEntity.getUpdatedAt());
        Assertions.assertEquals(anAccount.getDeletedAt(), actualEntity.getDeletedAt());
        Assertions.assertNull(actualEntity.getDeletedAt());
    }

    @Test
    public void givenAValidAccount_whenCallsUpdate_shouldReturnAUpdateAccount() {
        final var expectedName = "Minha Carteira";
        final var expectedUser = "usuarioTeste";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final var expectedBalance = 100;
        final var expectedIsActive = true;

        final var anAccount = Account.newAccount(
                "Name Teste", expectedUser, "Categoria Teste", "expectedSymbol", 0, expectedIsActive);


        Assertions.assertEquals(0, accountRepository.count());

        accountRepository.saveAndFlush(AccountJpaEntity.from(anAccount));

        Assertions.assertEquals(1, accountRepository.count());

        final var actualInvalidEntity = accountRepository.findById(anAccount.getId().getValue()).get();
        Assertions.assertEquals(anAccount.getId().getValue(), actualInvalidEntity.getId());
        Assertions.assertEquals("Name Teste", actualInvalidEntity.getName());
        Assertions.assertEquals("Categoria Teste", actualInvalidEntity.getCategory());
        Assertions.assertEquals("expectedSymbol", actualInvalidEntity.getSymbol());
        Assertions.assertEquals(0, actualInvalidEntity.getBalance());
        Assertions.assertEquals(expectedUser, actualInvalidEntity.getUser());
        Assertions.assertEquals(expectedIsActive, actualInvalidEntity.isActive());

        final var aUpdatedAccount = anAccount.clone()
                .update(expectedName, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        final var actualCategory = accountPostgreSQLGateway.update(aUpdatedAccount);

        Assertions.assertEquals(1, accountRepository.count());

        Assertions.assertEquals(anAccount.getId(), actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedCategory, actualCategory.getCategory());
        Assertions.assertEquals(expectedSymbol, actualCategory.getSymbol());
        Assertions.assertEquals(expectedBalance, actualCategory.getBalance());
        Assertions.assertEquals(expectedUser, actualCategory.getUser());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertEquals(anAccount.getCreatedAt(), actualCategory.getCreatedAt());
        Assertions.assertTrue(anAccount.getUpdatedAt().isBefore(actualCategory.getUpdatedAt()));
        Assertions.assertEquals(anAccount.getDeletedAt(), actualCategory.getDeletedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());

        final var actualEntity = accountRepository.findById(anAccount.getId().getValue()).get();
        Assertions.assertEquals(anAccount.getId().getValue(), actualEntity.getId());
        Assertions.assertEquals(expectedName, actualEntity.getName());
        Assertions.assertEquals(expectedCategory, actualEntity.getCategory());
        Assertions.assertEquals(expectedSymbol, actualEntity.getSymbol());
        Assertions.assertEquals(expectedBalance, actualEntity.getBalance());
        Assertions.assertEquals(expectedUser, actualEntity.getUser());
        Assertions.assertEquals(expectedIsActive, actualEntity.isActive());
        Assertions.assertEquals(anAccount.getCreatedAt(), actualEntity.getCreatedAt());
        Assertions.assertTrue(anAccount.getUpdatedAt().isBefore(actualEntity.getUpdatedAt()));
        Assertions.assertEquals(anAccount.getDeletedAt(), actualEntity.getDeletedAt());
        Assertions.assertNull(actualEntity.getDeletedAt());
    }

    @Test
    public void givenAPrePersistedAccountAndValidAccountId_whenTryToDeleteIt_shouldDeleteAccount() {
        final var anAccount = Account.newAccount(
                "Name Teste", "teste", "Categoria Teste", "expectedSymbol", 0, true);

        Assertions.assertEquals(0, accountRepository.count());

        accountRepository.saveAndFlush(AccountJpaEntity.from(anAccount));

        Assertions.assertEquals(1, accountRepository.count());

        accountPostgreSQLGateway.deleteById(anAccount.getId());

        Assertions.assertEquals(0, accountRepository.count());
    }

    @Test
    public void givenInvalidAccountId_whenTryToDeleteIt_shouldDeleteAccount() {
        Assertions.assertEquals(0, accountRepository.count());

        accountPostgreSQLGateway.deleteById(AccountID.from("invalid"));

        Assertions.assertEquals(0, accountRepository.count());
    }

    @Test
    public void givenAPrePersistedAccountAndValidAccountId_whenCallsFindById_shouldReturnAccount() {
        final var expectedName = "Minha Carteira";
        final var expectedUser = "usuarioTeste";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final var expectedBalance = 100;
        final var expectedIsActive = true;

        final var anAccount = Account.newAccount(
                expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        Assertions.assertEquals(0, accountRepository.count());

        accountRepository.saveAndFlush(AccountJpaEntity.from(anAccount));

        Assertions.assertEquals(1, accountRepository.count());

        final var actualAccount = accountPostgreSQLGateway.findById(anAccount.getId()).get();

        Assertions.assertEquals(1, accountRepository.count());

        Assertions.assertEquals(anAccount.getId(), actualAccount.getId());
        Assertions.assertEquals(expectedName, actualAccount.getName());
        Assertions.assertEquals(expectedCategory, actualAccount.getCategory());
        Assertions.assertEquals(expectedSymbol, actualAccount.getSymbol());
        Assertions.assertEquals(expectedBalance, actualAccount.getBalance());
        Assertions.assertEquals(expectedUser, actualAccount.getUser());
        Assertions.assertEquals(expectedIsActive, actualAccount.isActive());
        Assertions.assertEquals(anAccount.getCreatedAt(), actualAccount.getCreatedAt());
        Assertions.assertEquals(anAccount.getUpdatedAt(), actualAccount.getUpdatedAt());
        Assertions.assertEquals(anAccount.getDeletedAt(), actualAccount.getDeletedAt());
        Assertions.assertNull(actualAccount.getDeletedAt());
    }

    @Test
    public void givenAValidAccountIdNotStored_whenCallsFindById_shouldReturnEmpty() {
        Assertions.assertEquals(0, accountRepository.count());

        final var actualAccount = accountPostgreSQLGateway.findById(AccountID.from("empty"));

        Assertions.assertEquals(0, accountRepository.count());

        Assertions.assertTrue(actualAccount.isEmpty());
    }

    @Test
    public void givenAPrePersistedAccounts_whenCallsFindAllByUserOrderByNameAsc_shouldReturnListOfAccount() {
        final var anAccount1 = Account.newAccount(
                "teste nome", "teste user", "teste categoria", "teste symbol", 0, true);

        final var anAccount2 = Account.newAccount(
                "teste nome2", "teste user", "teste categoria2", "teste symbol2", 100, true);

        final var anAccount3 = Account.newAccount(
                "teste nome3", "teste user2", "teste categoria3", "teste symbol3", 100, true);


        Assertions.assertEquals(0, accountRepository.count());
        accountRepository.saveAll(
                List.of(
                        AccountJpaEntity.from(anAccount1),
                        AccountJpaEntity.from(anAccount2),
                        AccountJpaEntity.from(anAccount3)
                )
        );
        Assertions.assertEquals(3, accountRepository.count());

        final var actualResult = accountPostgreSQLGateway.findAllByUserOrderByNameAsc("teste user");

        Assertions.assertEquals(2, actualResult.size());
        Assertions.assertEquals(anAccount1.getId(), actualResult.get(0).getId());
        Assertions.assertEquals(anAccount2.getId(), actualResult.get(1).getId());
    }

    @Test
    public void givenEmptyAccountsTable_whenCallsFindAllByUserOrderByNameAsc_shouldReturnEmptyPage() {
        Assertions.assertEquals(0, accountRepository.count());
        final var actualResult = accountPostgreSQLGateway.findAllByUserOrderByNameAsc("teste user");
        Assertions.assertTrue(actualResult.isEmpty());
    }
}
