package com.lj.infrastructure.creditcard.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCardJpaEntity, String> {

    List<CreditCardJpaEntity> findAllByUserOrderByDescription(String anUser);

}
