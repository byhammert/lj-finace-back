package com.lj.domain.post;

import com.lj.domain.account.Account;
import com.lj.domain.exceptions.DomainException;
import com.lj.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PostTest {

    @Test
    public void givenAValidParams_whenCallNewPost_thenInstantiatedPost() {
        final var expectedDescription = "Descrição teste";
        final var expectedCategory = "categoria teste";
        final var expectedSubcategory = "Subcategoria teste";
        final var expectedRecurrence = RecurrenceType.FIXED;
        final var expectedAccount = "ContaTeste";
        final var expectedUser = "usuarioTeste";
        final var expectedNote = "Note teste";
        final var expectedPostType = PostType.CREDIT_CARD;
        final var expectedEffective = true;
        final var expectedAmount = 100;

        final var actualPost = Post.newPost(expectedDescription,
                                                    expectedCategory,
                                                    expectedSubcategory,
                                                    expectedRecurrence,
                                                    expectedAccount,
                                                    expectedUser,
                                                    expectedNote,
                                                    expectedPostType,
                                                    expectedEffective,
                                                    expectedAmount);

        Assertions.assertNotNull(actualPost);
        Assertions.assertNotNull(actualPost.getId());
        Assertions.assertNotNull(actualPost.getCreatedAt());
        Assertions.assertNotNull(actualPost.getUpdatedAt());
        Assertions.assertEquals(expectedDescription, actualPost.getDescription());
        Assertions.assertEquals(expectedCategory, actualPost.getCategory());
        Assertions.assertEquals(expectedSubcategory, actualPost.getSubcategory());
        Assertions.assertEquals(expectedRecurrence, actualPost.getRecurrence());
        Assertions.assertEquals(expectedAccount, actualPost.getAccount());
        Assertions.assertEquals(expectedUser, actualPost.getUser());
        Assertions.assertEquals(expectedNote, actualPost.getNote());
        Assertions.assertEquals(expectedPostType, actualPost.getPostType());
        Assertions.assertEquals(expectedEffective, actualPost.isEffective());
        Assertions.assertEquals(expectedAmount, actualPost.getAmount());

    }

    @Test
    public void givenAnInvalidNullDescription_whenCallNewPostAndValidate_thenShouldReceiveError() {
        final String expectedDescription = null;
        final var expectedCategory = "categoria teste";
        final var expectedSubcategory = "Subcategoria teste";
        final var expectedRecurrence = RecurrenceType.FIXED;
        final var expectedAccount = "ContaTeste";
        final var expectedUser = "usuarioTeste";
        final var expectedNote = "Note teste";
        final var expectedPostType = PostType.CREDIT_CARD;
        final var expectedEffective = true;
        final var expectedAmount = 100;
        final var expectedErrorMessage = "'description' should not be null";
        final var expectedErrorCount = 1;

        final var actualPost = Post.newPost(expectedDescription,
                expectedCategory,
                expectedSubcategory,
                expectedRecurrence,
                expectedAccount,
                expectedUser,
                expectedNote,
                expectedPostType,
                expectedEffective,
                expectedAmount);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualPost.validate(new ThrowsValidationHandler()));


        Assertions.assertNotNull(actualException);
        Assertions.assertNotNull(actualException.getErrors());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());

    }
}
