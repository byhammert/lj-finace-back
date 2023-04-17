package com.lj.infrastructure.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lj.ControllerTest;
import com.lj.application.account.create.CreateAccountUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

//@ControllerTest(controllers = AccountAPI.class)
public class AccountAPITest {

//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private CreateAccountUseCase createAccountUseCase;
//
//
//    @Test
//    public void givenAValidCommand_whenCallsCreateAccount_thenShouldReturnAccountId() throws Exception {

//        final var expectedName = "Minha Carteira";
//        final var expectedUser = "usuarioTeste";
//        final var expectedCategory = "Investimento";
//        final var expectedSymbol = "NUBANK";
//        final long expectedBalance = 0;
//        final var expectedIsActive = true;
//
//        final var anInput =
//                new CreateAccountApiInput(expectedName, expectedCategory, expectedSymbol, expectedBalance, expectedIsActive);
//
//        when(createAccountUseCase.execute(any()))
//                .thenReturn(Right(CreateAccountOutput.from("123")));
//
//        final var request =MockMvcRequestBuilders.post("/accounts")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(this.objectMapper.writeValueAsString(anInput));
//
//
//        this.mvc.perform(request)
//                .andDo(print())
//                .andExpectAll(
//                        status().isCreated(),
//                        header().string("Location", "/accounts/123")
//                );
//
//        verify(createAccountUseCase, times(1)).execute(argThat(cmd ->
//                Objects.equals(expectedName, cmd.name())
//                && Objects.equals(expectedUser, cmd.user())
//                        && Objects.equals(expectedCategory, cmd.category())
//                        && Objects.equals(expectedSymbol, cmd.symbol())
//                        && Objects.equals(expectedBalance, cmd.balance())
//                        && Objects.equals(expectedIsActive, cmd.isActive())
//                ));
//    }
}
