package com.lj.infrastructure.configuration.usecases;

import com.lj.application.account.create.CreateAccountUseCase;
import com.lj.application.account.create.DefaultCreateAccountUseCase;
import com.lj.application.account.delete.DefaultDeleteAccountUseCase;
import com.lj.application.account.delete.DeleteAccountUseCase;
import com.lj.application.account.retrieve.get.DefaultGetAccountByIdUseCase;
import com.lj.application.account.retrieve.get.GetAccountByIdUseCase;
import com.lj.application.account.retrieve.list.DefaultListAccountsUseCase;
import com.lj.application.account.retrieve.list.ListAccountsUseCase;
import com.lj.application.account.update.DefaultUpdateAccountUseCase;
import com.lj.application.account.update.UpdateAccountUseCase;
import com.lj.application.account.update.addamount.DefaultUpdateAccountAddAmountUseCase;
import com.lj.application.account.update.addamount.UpdateAccountAddAmountUseCase;
import com.lj.domain.account.AccountGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountUseCaseConfig {

    private final AccountGateway accountGateway;

    public AccountUseCaseConfig(final AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    @Bean
    public CreateAccountUseCase createAccountUseCase() {
        return new DefaultCreateAccountUseCase(accountGateway);
    }

    @Bean
    public UpdateAccountUseCase updateAccountUseCase() {
        return new DefaultUpdateAccountUseCase(accountGateway);
    }

    @Bean
    public DeleteAccountUseCase deleteAccountUseCase() {
        return new DefaultDeleteAccountUseCase(accountGateway);
    }

    @Bean
    public GetAccountByIdUseCase getAccountByIdUseCase() {
        return new DefaultGetAccountByIdUseCase(accountGateway);
    }

    @Bean
    public ListAccountsUseCase listCategoriesUseCase() {
        return new DefaultListAccountsUseCase(accountGateway);
    }

    @Bean
    public UpdateAccountAddAmountUseCase updateAccountAddAmountUseCase() {
        return new DefaultUpdateAccountAddAmountUseCase(accountGateway);
    }
}
