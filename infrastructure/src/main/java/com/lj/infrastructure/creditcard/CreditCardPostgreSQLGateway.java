package com.lj.infrastructure.creditcard;

import com.lj.domain.creditcard.CreditCard;
import com.lj.domain.creditcard.CreditCardGateway;
import com.lj.domain.creditcard.CreditCardID;
import com.lj.infrastructure.creditcard.persistence.CreditCardJpaEntity;
import com.lj.infrastructure.creditcard.persistence.CreditCardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CreditCardPostgreSQLGateway implements CreditCardGateway {

    private final CreditCardRepository repository;

    public CreditCardPostgreSQLGateway(CreditCardRepository repository) {
        this.repository = repository;
    }

    @Override
    public CreditCard create(CreditCard aCreditCard) {
        return save(aCreditCard);
    }

    @Override
    public void deleteById(CreditCardID anId) {
        final var anIdValue = anId.getValue();

        if(this.repository.existsById(anIdValue)) {
            repository.deleteById(anId.getValue());
        }
    }

    @Override
    public Optional<CreditCard> findById(CreditCardID anId) {
        return this.repository.findById(anId.getValue())
                .map(CreditCardJpaEntity::toAggregate);
    }

    @Override
    public CreditCard update(CreditCard anCreditCard) {
        return save(anCreditCard);
    }

    @Override
    public List<CreditCard> findAllByUserOrderByDescription(String anUser) {
        List<CreditCard> result = new ArrayList<>();
        List<CreditCardJpaEntity> creditCardJpaEntities = this.repository.findAllByUserOrderByDescription(anUser);
        if (creditCardJpaEntities != null && !creditCardJpaEntities.isEmpty())
            result = creditCardJpaEntities.stream().map(CreditCardJpaEntity::toAggregate).toList();
        return result;
    }

    private CreditCard save(final CreditCard aCreditCard) {
        return this.repository.save(CreditCardJpaEntity.from(aCreditCard)).toAggregate();
    }
}
