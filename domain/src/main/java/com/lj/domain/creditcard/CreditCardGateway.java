package com.lj.domain.creditcard;

import java.util.List;
import java.util.Optional;

public interface CreditCardGateway {

    CreditCard create(CreditCard aCreditCard);
    void deleteById(CreditCardID anId);
    Optional<CreditCard> findById(CreditCardID anId);
    CreditCard update(CreditCard aCreditCard);
    List<CreditCard> findAllByUserOrderByDescription(String anUser);

}
