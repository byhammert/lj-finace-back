package com.lj.infrastructure.account.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository  extends JpaRepository<AccountJpaEntity, String> {

    List<AccountJpaEntity> findAllByUserOrderByNameAsc(String user);
}
