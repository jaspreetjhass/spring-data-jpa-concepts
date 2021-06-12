package org.jp.spring.data.jpa.inheritance.mapping.starter.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.jp.spring.data.jpa.inheritance.mapping.starter.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository<T extends Payment> extends JpaRepository<T, Long> {

	List<Payment> findByAmountBetween(BigDecimal min,BigDecimal max);
	
}
