package com.lj.domain.account;

import com.lj.domain.pagination.Pagination;

import java.util.List;
import java.util.Optional;

public interface AccountGateway {
    Account create(Account anAccount);
    void deleteById(AccountID anId);
    Optional<Account> findById(AccountID anId);
    Account update(Account anAccount);
    List<Account> findAllByUserOrderByNameAsc(String anUser);
}
