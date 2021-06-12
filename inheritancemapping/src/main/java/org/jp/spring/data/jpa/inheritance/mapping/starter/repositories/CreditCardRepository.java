package org.jp.spring.data.jpa.inheritance.mapping.starter.repositories;

import org.jp.spring.data.jpa.inheritance.mapping.starter.entity.CreditCard;

public interface CreditCardRepository extends PaymentRepository<CreditCard> {

}