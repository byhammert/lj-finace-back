package com.lj.infrastructure.account;

import com.lj.domain.account.Account;
import com.lj.domain.account.AccountGateway;
import com.lj.domain.account.AccountID;
import com.lj.infrastructure.account.persistence.AccountJpaEntity;
import com.lj.infrastructure.account.persistence.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class AccountPostgreSQLGateway implements AccountGateway {

    private final AccountRepository repository;

    public AccountPostgreSQLGateway(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Account create(Account anAccount) {
        return save(anAccount);
    }

    @Override
    public void deleteById(AccountID anId) {
        final var anIdValue = anId.getValue();

        if(this.repository.existsById(anIdValue)) {
            repository.deleteById(anId.getValue());
        }
    }

    @Override
    public Optional<Account> findById(AccountID anId) {
        return this.repository.findById(anId.getValue())
                .map(AccountJpaEntity::toAggregate);
    }

    @Override
    public Account update(Account anAccount) {
        return save(anAccount);
    }

    @Override
    public List<Account> findAllByUserOrderByNameAsc(String anUser) {
        List<Account> result = new ArrayList<>();
        List<AccountJpaEntity> accountJpaEntities = this.repository.findAllByUserOrderByNameAsc(anUser);
        if (accountJpaEntities != null && !accountJpaEntities.isEmpty())
            result = accountJpaEntities.stream().map(AccountJpaEntity::toAggregate).toList();
        return result;
    }

    private Account save(final Account anAccount) {
        return this.repository.save(AccountJpaEntity.from(anAccount)).toAggregate();
    }
}
