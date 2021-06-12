package org.jp.spring.data.jpa.component.mapping.repository;

import java.util.Date;
import java.util.List;

import org.jp.spring.data.jpa.component.mapping.entity.Payment;
import org.jp.spring.data.jpa.component.mapping.entity.PaymentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, PaymentId> {

	List<Payment> findByTransactionTimeBetween(Date from, Date to);
}
