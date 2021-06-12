package org.jp.spring.data.jpa.inheritance.mapping.starter.repositories;

import java.util.Optional;

import org.jp.spring.data.jpa.inheritance.mapping.starter.entity.Cheque;
import org.jp.spring.data.jpa.inheritance.mapping.starter.entity.Payment;

public interface ChequeRepository extends PaymentRepository<Cheque> {

	Optional<Payment> findByChequeNo(String chequeNo);

}
