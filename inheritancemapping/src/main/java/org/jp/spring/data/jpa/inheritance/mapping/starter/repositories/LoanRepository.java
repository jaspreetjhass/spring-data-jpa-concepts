package org.jp.spring.data.jpa.inheritance.mapping.starter.repositories;

import java.util.List;

import org.jp.spring.data.jpa.inheritance.mapping.starter.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository<T extends Loan> extends JpaRepository<T, Integer> {

	List<Loan> findByInterestRateBetween(double min,double max);
	
	List<Loan> findByProcessingFeeLessThan(double processingFee);
	
}
