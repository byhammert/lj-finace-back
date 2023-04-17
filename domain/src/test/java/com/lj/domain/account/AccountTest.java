package com.lj.domain.account;

import com.lj.domain.exceptions.DomainException;
import com.lj.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountTest {

    @Test
    public void givenAValidParams_whenCallNewAccount_thenInstantiateAAccount() {
        final var expectedName = "Minha Carteira";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final var expectedBalance = 0;
        final var expectedIsActive = true;
        final var expectedUser = "usuarioTeste";

        final var actualAccount = Account.newAccount(
                expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        Assertions.assertNotNull(actualAccount);
        Assertions.assertNotNull(actualAccount.getId());
        Assertions.assertNotNull(actualAccount.getCreatedAt());
        Assertions.assertNotNull(actualAccount.getUpdatedAt());
        Assertions.assertNull(actualAccount.getDeletedAt());
        Assertions.assertEquals(expectedName, actualAccount.getName());
        Assertions.assertEquals(expectedUser, actualAccount.getUser());
        Assertions.assertEquals(expectedCategory, actualAccount.getCategory());
        Assertions.assertEquals(expectedBalance, actualAccount.getBalance());
        Assertions.assertEquals(expectedSymbol, actualAccount.getSymbol());
        Assertions.assertEquals(expectedIsActive, actualAccount.isActive());
    }

    @Test
    public void givenAnInvalidNullUser_whenCallNewAccountAndValidate_thenShouldReceiveError() {
        final var expectedName = "Minha Carteira";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final var expectedBalance = 0;
        final var expectedIsActive = true;
        final String expectedUser = null;

        final var expectedErrorMessage = "'user' should not be null";
        final var expectedErrorCount = 1;

        final var actualAccount = Account.newAccount(
                expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualAccount.validate(new ThrowsValidationHandler()));


        Assertions.assertNotNull(actualException);
        Assertions.assertNotNull(actualException.getErrors());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNullName_whenCallNewAccountAndValidate_thenShouldReceiveError() {
        final String expectedName = null;
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final var expectedBalance = 0;
        final var expectedIsActive = true;
        final var expectedUser = "usuarioTeste";

        final var expectedErrorMessage = "'name' should not be null";
        final var expectedErrorCount = 1;

        final var actualAccount = Account.newAccount(
                expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualAccount.validate(new ThrowsValidationHandler()));


        Assertions.assertNotNull(actualException);
        Assertions.assertNotNull(actualException.getErrors());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNullCategory_whenCallNewAccountAndValidate_thenShouldReceiveError() {
        final var expectedName = "Minha Carteira";
        final String expectedCategory = null;
        final var expectedSymbol = "NUBANK";
        final var expectedBalance = 0;
        final var expectedIsActive = true;
        final var expectedUser = "usuarioTeste";

        final var expectedErrorMessage = "'category' should not be null";
        final var expectedErrorCount = 1;

        final var actualAccount = Account.newAccount(
                expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualAccount.validate(new ThrowsValidationHandler()));


        Assertions.assertNotNull(actualException);
        Assertions.assertNotNull(actualException.getErrors());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNullSymbol_whenCallNewAccountAndValidate_thenShouldReceiveError() {
        final var expectedName = "Minha Carteira";
        final var expectedCategory = "Investimento";
        final String expectedSymbol = null;
        final var expectedBalance = 0;
        final var expectedIsActive = true;
        final var expectedUser = "usuarioTeste";

        final var expectedErrorMessage = "'symbol' should not be null";
        final var expectedErrorCount = 1;

        final var actualAccount = Account.newAccount(
                expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualAccount.validate(new ThrowsValidationHandler()));


        Assertions.assertNotNull(actualException);
        Assertions.assertNotNull(actualException.getErrors());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInValidEmptyName_whenCallNewAccountAndValidate_thenShouldReceiveError() {
        final var expectedName = " ";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final var expectedBalance = 0;
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'name' should not be empty";
        final var expectedErrorCount = 1;
        final var expectedUser = "usuarioTeste";

        final var actualAccount = Account.newAccount(
                expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualAccount.validate(new ThrowsValidationHandler()));


        Assertions.assertNotNull(actualException);
        Assertions.assertNotNull(actualException.getErrors());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInValidEmptyUser_whenCallNewAccountAndValidate_thenShouldReceiveError() {
        final var expectedName = "Minha Carteira";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final var expectedBalance = 0;
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'user' should not be empty";
        final var expectedErrorCount = 1;
        final var expectedUser = " ";

        final var actualAccount = Account.newAccount(
                expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualAccount.validate(new ThrowsValidationHandler()));


        Assertions.assertNotNull(actualException);
        Assertions.assertNotNull(actualException.getErrors());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInValidEmptyCategory_whenCallNewAccountAndValidate_thenShouldReceiveError() {
        final var expectedName = "Minha Carteira";
        final var expectedCategory = " ";
        final var expectedSymbol = "NUBANK";
        final var expectedBalance = 0;
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'category' should not be empty";
        final var expectedErrorCount = 1;
        final var expectedUser = "usuarioTeste";

        final var actualAccount = Account.newAccount(
                expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualAccount.validate(new ThrowsValidationHandler()));


        Assertions.assertNotNull(actualException);
        Assertions.assertNotNull(actualException.getErrors());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInValidEmptySymbol_whenCallNewAccountAndValidate_thenShouldReceiveError() {
        final var expectedName = "Minha Carteira";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = " ";
        final var expectedBalance = 0;
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'symbol' should not be empty";
        final var expectedErrorCount = 1;
        final var expectedUser = "usuarioTeste";

        final var actualAccount = Account.newAccount(
                expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualAccount.validate(new ThrowsValidationHandler()));


        Assertions.assertNotNull(actualException);
        Assertions.assertNotNull(actualException.getErrors());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInValidNameLengthLessThan3_whenCallNewAccountAndValidate_thenShouldReceiveError() {
        final var expectedName = "Mi ";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = " ";
        final var expectedBalance = 0;
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'name' must be between 3 and 20 characters";
        final var expectedErrorCount = 1;
        final var expectedUser = "usuarioTeste";

        final var actualAccount = Account.newAccount(
                expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualAccount.validate(new ThrowsValidationHandler()));


        Assertions.assertNotNull(actualException);
        Assertions.assertNotNull(actualException.getErrors());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInValidCategoyLengthLessThan3_whenCallNewAccountAndValidate_thenShouldReceiveError() {
        final var expectedName = "Minha Carteira";
        final var expectedCategory = "In ";
        final var expectedSymbol = " ";
        final var expectedBalance = 0;
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'category' must be between 3 and 20 characters";
        final var expectedErrorCount = 1;
        final var expectedUser = "usuarioTeste";

        final var actualAccount = Account.newAccount(
                expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualAccount.validate(new ThrowsValidationHandler()));


        Assertions.assertNotNull(actualException);
        Assertions.assertNotNull(actualException.getErrors());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInValidNameLengthMoreThan20_whenCallNewAccountAndValidate_thenShouldReceiveError() {
        final var expectedName = "Nunca é demais lembrar o peso e o ";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = " ";
        final var expectedBalance = 0;
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'name' must be between 3 and 20 characters";
        final var expectedErrorCount = 1;
        final var expectedUser = "usuarioTeste";

        final var actualAccount = Account.newAccount(
                expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualAccount.validate(new ThrowsValidationHandler()));


        Assertions.assertNotNull(actualException);
        Assertions.assertNotNull(actualException.getErrors());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInValidCategoryLengthMoreThan20_whenCallNewAccountAndValidate_thenShouldReceiveError() {
        final var expectedName = "Minha Carteira";
        final var expectedCategory = "Nunca é demais lembrar o peso e o ";
        final var expectedSymbol = " ";
        final var expectedBalance = 0;
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'category' must be between 3 and 20 characters";
        final var expectedErrorCount = 1;
        final var expectedUser = "usuarioTeste";

        final var actualAccount = Account.newAccount(
                expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualAccount.validate(new ThrowsValidationHandler()));


        Assertions.assertNotNull(actualException);
        Assertions.assertNotNull(actualException.getErrors());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInValidSymbolLengthMoreThan20_whenCallNewAccountAndValidate_thenShouldReceiveError() {
        final var expectedName = "Minha Carteira";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "Nunca é demais lembrar o peso e o a";
        final var expectedBalance = 0;
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'symbol' must be a maximum of 20 characters";
        final var expectedErrorCount = 1;
        final var expectedUser = "usuarioTeste";

        final var actualAccount = Account.newAccount(
                expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualAccount.validate(new ThrowsValidationHandler()));


        Assertions.assertNotNull(actualException);
        Assertions.assertNotNull(actualException.getErrors());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInValidUserLengthMoreThan255_whenCallNewAccountAndValidate_thenShouldReceiveError() {
        final var expectedName = "Minha Carteira";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "Nunc";
        final var expectedBalance = 0;
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'user' must be a maximum of 255 characters";
        final var expectedErrorCount = 1;
        final var expectedUser = """
        Nunca é demais lembrar o peso e o significado destes problemas, uma vez que o início da atividade 
        geral de formação de atitudes auxilia a preparação e a composição das formas de ação. O que temos que ter sempre em mente é que temos que ter sempre em mente é que
        """;

        final var actualAccount = Account.newAccount(
                expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualAccount.validate(new ThrowsValidationHandler()));


        Assertions.assertNotNull(actualException);
        Assertions.assertNotNull(actualException.getErrors());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAValidFalseActive_whenCallNewAccountAndValidate_thenInstantiateAnAccount() {
        final var expectedName = "Minha Carteira";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final var expectedBalance = 0;
        final var expectedIsActive = false;
        final var expectedUser = "usuarioTeste";

        final var actualAccount = Account.newAccount(
                expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualAccount.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(actualAccount);
        Assertions.assertNotNull(actualAccount.getId());
        Assertions.assertNotNull(actualAccount.getCreatedAt());
        Assertions.assertNotNull(actualAccount.getUpdatedAt());
        Assertions.assertNotNull(actualAccount.getDeletedAt());
        Assertions.assertEquals(expectedName, actualAccount.getName());
        Assertions.assertEquals(expectedCategory, actualAccount.getCategory());
        Assertions.assertEquals(expectedSymbol, actualAccount.getSymbol());
        Assertions.assertEquals(expectedBalance, actualAccount.getBalance());
        Assertions.assertEquals(expectedIsActive, actualAccount.isActive());
    }

    @Test
    public void givenAValidActiveAccount_whenCallDeactivate_thenReturnAccountInactivated() {
        final var expectedName = "Minha Carteira";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final var expectedBalance = 0;
        final var expectedIsActive = false;
        final var expectedUser = "usuarioTeste";

        final var anAccount = Account.newAccount(
                expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, true);

        Assertions.assertDoesNotThrow(() -> anAccount.validate(new ThrowsValidationHandler()));

        final var createdAt = anAccount.getCreatedAt();
        final var updatedAt = anAccount.getUpdatedAt();

        Assertions.assertTrue(anAccount.isActive());
        Assertions.assertNull(anAccount.getDeletedAt());

        final var actualAccount = anAccount.deactivate();

        Assertions.assertDoesNotThrow(() -> actualAccount.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(anAccount.getId(), actualAccount.getId());
        Assertions.assertEquals(anAccount.getName(), actualAccount.getName());
        Assertions.assertEquals(anAccount.getCategory(), actualAccount.getCategory());
        Assertions.assertEquals(anAccount.getSymbol(), actualAccount.getSymbol());
        Assertions.assertEquals(anAccount.getBalance(), actualAccount.getBalance());
        Assertions.assertEquals(expectedIsActive, actualAccount.isActive());
        Assertions.assertEquals(createdAt, actualAccount.getCreatedAt());
        Assertions.assertTrue(anAccount.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(actualAccount.getDeletedAt());
    }

    @Test
    public void givenAValidInactiveAccount_whenCallActivate_thenReturnAccountActivated() {
        final var expectedName = "Minha Carteira";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final var expectedBalance = 0;
        final var expectedIsActive = true;
        final var expectedUser = "usuarioTeste";

        final var anAccount = Account.newAccount(
                expectedName, expectedUser, expectedCategory, expectedSymbol, expectedBalance, false);

        Assertions.assertDoesNotThrow(() -> anAccount.validate(new ThrowsValidationHandler()));

        final var createdAt = anAccount.getCreatedAt();
        final var updatedAt = anAccount.getUpdatedAt();

        Assertions.assertFalse(anAccount.isActive());
        Assertions.assertNotNull(anAccount.getDeletedAt());

        final var actualAccount = anAccount.activate();

        Assertions.assertDoesNotThrow(() -> actualAccount.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(anAccount.getId(), actualAccount.getId());
        Assertions.assertEquals(anAccount.getName(), actualAccount.getName());
        Assertions.assertEquals(anAccount.getCategory(), actualAccount.getCategory());
        Assertions.assertEquals(anAccount.getSymbol(), actualAccount.getSymbol());
        Assertions.assertEquals(anAccount.getBalance(), actualAccount.getBalance());
        Assertions.assertEquals(expectedIsActive, actualAccount.isActive());
        Assertions.assertEquals(createdAt, actualAccount.getCreatedAt());
        Assertions.assertTrue(anAccount.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualAccount.getDeletedAt());
    }

    @Test
    public void givenAValidAccount_whenCallUpdate_thenReturnAccountUpdated() {
        final var expectedName = "Minha Carteira";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final var expectedBalance = 100;
        final var expectedIsActive = true;
        final var expectedUser = "usuarioTeste";

        final var anAccount = Account.newAccount(
                "Teste Nome", expectedUser, "Teste Categoria", "Teste Symbol", 0, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> anAccount.validate(new ThrowsValidationHandler()));

        final var createdAt = anAccount.getCreatedAt();
        final var updatedAt = anAccount.getUpdatedAt();

        final var actualAccount = anAccount.update(expectedName, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualAccount.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(anAccount.getId(), actualAccount.getId());
        Assertions.assertEquals(expectedName, actualAccount.getName());
        Assertions.assertEquals(expectedCategory, actualAccount.getCategory());
        Assertions.assertEquals(expectedSymbol, actualAccount.getSymbol());
        Assertions.assertEquals(expectedBalance, actualAccount.getBalance());
        Assertions.assertEquals(expectedUser, actualAccount.getUser());
        Assertions.assertEquals(expectedIsActive, actualAccount.isActive());
        Assertions.assertEquals(createdAt, actualAccount.getCreatedAt());
        Assertions.assertTrue(actualAccount.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualAccount.getDeletedAt());

    }

    @Test
    public void givenAValidAccount_whenCallUpdateToInactive_thenReturnAccountUpdated() {
        final var expectedName = "Minha Carteira";
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final var expectedBalance = 100;
        final var expectedIsActive = false;
        final var expectedUser = "usuarioTeste";

        final var anAccount = Account.newAccount(
                "Teste Nome", expectedUser, "Teste Categoria", "Teste Symbol", 0, true);

        Assertions.assertDoesNotThrow(() -> anAccount.validate(new ThrowsValidationHandler()));

        final var createdAt = anAccount.getCreatedAt();
        final var updatedAt = anAccount.getUpdatedAt();

        Assertions.assertTrue(anAccount.isActive());
        Assertions.assertNull(anAccount.getDeletedAt());

        final var actualAccount =
                anAccount.update(expectedName, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualAccount.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(anAccount.getId(), actualAccount.getId());
        Assertions.assertEquals(expectedName, actualAccount.getName());
        Assertions.assertEquals(expectedCategory, actualAccount.getCategory());
        Assertions.assertEquals(expectedSymbol, actualAccount.getSymbol());
        Assertions.assertEquals(expectedBalance, actualAccount.getBalance());
        Assertions.assertEquals(expectedUser, actualAccount.getUser());
        Assertions.assertEquals(expectedIsActive, actualAccount.isActive());
        Assertions.assertEquals(createdAt, actualAccount.getCreatedAt());
        Assertions.assertTrue(actualAccount.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(actualAccount.getDeletedAt());
    }

    @Test
    public void givenAValidAccount_whenCallUpdateWithInvalidParams_thenReturnAccountUpdated() {
        final String expectedName = null;
        final var expectedCategory = "Investimento";
        final var expectedSymbol = "NUBANK";
        final var expectedBalance = 100;
        final var expectedIsActive = true;
        final var expectedUser = "usuarioTeste";

        final var anAccount = Account.newAccount(
                "Teste", expectedUser, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> anAccount.validate(new ThrowsValidationHandler()));

        final var createdAt = anAccount.getCreatedAt();
        final var updatedAt = anAccount.getUpdatedAt();

        final var actualAccount =
                anAccount.update(expectedName, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);

        Assertions.assertEquals(anAccount.getId(), actualAccount.getId());
        Assertions.assertEquals(expectedName, actualAccount.getName());
        Assertions.assertEquals(expectedCategory, actualAccount.getCategory());
        Assertions.assertEquals(expectedSymbol, actualAccount.getSymbol());
        Assertions.assertEquals(expectedBalance, actualAccount.getBalance());
        Assertions.assertEquals(expectedUser, actualAccount.getUser());
        Assertions.assertEquals(expectedIsActive, actualAccount.isActive());
        Assertions.assertEquals(createdAt, actualAccount.getCreatedAt());
        Assertions.assertTrue(actualAccount.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualAccount.getDeletedAt());
    }
}
