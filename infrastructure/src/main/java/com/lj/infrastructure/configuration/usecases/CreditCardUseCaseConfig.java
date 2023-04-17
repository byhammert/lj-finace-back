package com.lj.infrastructure.configuration.usecases;

import com.lj.application.creditcard.create.CreateCreditCardUseCase;
import com.lj.application.creditcard.create.DefaultCreateCreditCardUseCase;
import com.lj.domain.creditcard.CreditCardGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreditCardUseCaseConfig {
    private final CreditCardGateway creditCardGateway;

    public CreditCardUseCaseConfig(CreditCardGateway creditCardGateway) {
        this.creditCardGateway = creditCardGateway;
    }

    @Bean
    public CreateCreditCardUseCase createCreditCardUseCase() {
        return new DefaultCreateCreditCardUseCase(creditCardGateway);
    }
}
