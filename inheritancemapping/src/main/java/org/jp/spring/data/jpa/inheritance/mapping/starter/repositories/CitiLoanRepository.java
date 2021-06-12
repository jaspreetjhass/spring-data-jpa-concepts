package org.jp.spring.data.jpa.inheritance.mapping.starter.repositories;

import java.util.List;

import org.jp.spring.data.jpa.inheritance.mapping.starter.entity.CitiLoan;

public interface CitiLoanRepository extends LoanRepository<CitiLoan> {

	List<CitiLoan> findByMortgage(String mortgage);
}
