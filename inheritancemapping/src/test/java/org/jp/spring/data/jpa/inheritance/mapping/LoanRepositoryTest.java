package org.jp.spring.data.jpa.inheritance.mapping;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.jp.spring.data.jpa.inheritance.mapping.starter.InheritanceMappingApplication;
import org.jp.spring.data.jpa.inheritance.mapping.starter.entity.CitiLoan;
import org.jp.spring.data.jpa.inheritance.mapping.starter.entity.IciciLoan;
import org.jp.spring.data.jpa.inheritance.mapping.starter.entity.Loan;
import org.jp.spring.data.jpa.inheritance.mapping.starter.repositories.CitiLoanRepository;
import org.jp.spring.data.jpa.inheritance.mapping.starter.repositories.IciciLoanRepository;
import org.jp.spring.data.jpa.inheritance.mapping.starter.repositories.LoanRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = InheritanceMappingApplication.class)
@DataJpaTest(bootstrapMode = BootstrapMode.DEFAULT)
public class LoanRepositoryTest {

	@Autowired
	private LoanRepository<Loan> loanRepository;
	@Autowired
	private CitiLoanRepository citiLoanRepository;
	@Autowired
	private IciciLoanRepository iciciLoanRepository;

	@Test
	public void findAllLoanLiesUnderInterestRange() {
		final CitiLoan loan1 = CitiLoan.builder().interestRate(8.4).processingFee(1000).build();
		final CitiLoan loan2 = CitiLoan.builder().interestRate(5.4).processingFee(700).build();
		
		final IciciLoan loan3 = IciciLoan.builder().interestRate(11.4).processingFee(500).build();
		final IciciLoan loan4 = IciciLoan.builder().interestRate(7.4).processingFee(400).build();
		
		loanRepository.saveAll(Arrays.asList(loan1,loan2,loan3,loan4));
		
		final List<Loan> loanList = loanRepository.findByInterestRateBetween(6.5, 9.0);
		assertThat(loanList).hasSize(2);
	}
	
	@Test
	public void findAllCitiLoanByMortage() {
		final CitiLoan loan1 = CitiLoan.builder().interestRate(8.4).processingFee(1000).mortgage("mortgage01").build();
		final CitiLoan loan2 = CitiLoan.builder().interestRate(5.4).processingFee(700).mortgage("mortgage02").build();

		citiLoanRepository.saveAll(Arrays.asList(loan1,loan2));
		
		final List<CitiLoan> loanList = citiLoanRepository.findByMortgage("mortgage01");
		assertThat(loanList).hasSize(1);
	}
	
	@Test
	public void findByProcessingFeeLessThan() {
		final IciciLoan loan1 = IciciLoan.builder().interestRate(11.4).processingFee(500).build();
		final IciciLoan loan2 = IciciLoan.builder().interestRate(7.4).processingFee(400).build();
		
		loanRepository.saveAll(Arrays.asList(loan1,loan2));
		
		final List<Loan> loanList = iciciLoanRepository.findByProcessingFeeLessThan(500);
		assertThat(loanList).hasSize(1).map(Loan::getInterestRate).containsOnlyOnce(7.4);
	}

}
