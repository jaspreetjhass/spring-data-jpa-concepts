package org.jp.spring.data.jpa.inheritance.mapping;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.jp.spring.data.jpa.inheritance.mapping.starter.InheritanceMappingApplication;
import org.jp.spring.data.jpa.inheritance.mapping.starter.entity.Cheque;
import org.jp.spring.data.jpa.inheritance.mapping.starter.entity.CreditCard;
import org.jp.spring.data.jpa.inheritance.mapping.starter.entity.Payment;
import org.jp.spring.data.jpa.inheritance.mapping.starter.repositories.ChequeRepository;
import org.jp.spring.data.jpa.inheritance.mapping.starter.repositories.CreditCardRepository;
import org.jp.spring.data.jpa.inheritance.mapping.starter.repositories.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = InheritanceMappingApplication.class)
@DataJpaTest(bootstrapMode = BootstrapMode.LAZY)
public class PaymentRepositoryTest {

	@Autowired
	private PaymentRepository<Payment> paymentRepository;
	@Autowired
	private ChequeRepository chequeRepository;
	@Autowired
	private CreditCardRepository creditCardRepository;
	
	@Test
	public void findAllPaymentModeLiesUnderAmountRange()
	{
		final Cheque chequePayment = Cheque.builder().amount(new BigDecimal(200)).chequeNo("HJH123233223").build();
		chequeRepository.save(chequePayment);
		final CreditCard creditCardPayment = CreditCard.builder().amount(new BigDecimal(1000))
				.creditCardNo(new BigInteger("49583480304930")).build();
		creditCardRepository.save(creditCardPayment);
		
		final List<Payment> paymentList = paymentRepository.findByAmountBetween(new BigDecimal(200), new BigDecimal(2000));
		paymentList.forEach(System.out::println);
	}
	
	@Test
	public void makePaymentByCheque() {
		final Cheque chequePayment = Cheque.builder().amount(new BigDecimal(343445)).chequeNo("HJH123233223").build();
		chequeRepository.save(chequePayment);
		chequeRepository.findAll().forEach(System.out::println);
		assertThat(chequeRepository.findAll()).isNotEmpty().map(c -> ((Cheque) c).getChequeNo())
				.allMatch(cq -> cq.equals("HJH123233223"));
	}
	
	@Test
	public void findByChequeNo() {
		final Cheque chequePayment = Cheque.builder().amount(new BigDecimal(343445)).chequeNo("ASH28378298201").build();
		chequeRepository.save(chequePayment);
		chequeRepository.findByChequeNo("ASH28378298201").ifPresent(System.out::println);
		assertThat(chequeRepository.findByChequeNo("ASH28378298201")).isNotEmpty().map(c -> ((Cheque) c).getChequeNo())
				.get().isEqualTo("ASH28378298201");
	}

	@Test
	public void makePaymentByCreditCard() {
		final CreditCard creditCardPayment = CreditCard.builder().amount(new BigDecimal(100))
				.creditCardNo(new BigInteger("49583480304930")).build();
		creditCardRepository.save(creditCardPayment);
		creditCardRepository.findAll().forEach(System.out::println);
		assertThat(creditCardRepository.findAll()).map(c -> ((CreditCard) c).getCreditCardNo())
				.anyMatch(cc -> cc.equals(new BigInteger("49583480304930")));
	}
	
	@Test
	public void findChequeAmountBetweenRange()
	{
		final Cheque chequePayment1 = Cheque.builder().amount(new BigDecimal(200)).chequeNo("AHJ28378298201").build();
		final Cheque chequePayment2 = Cheque.builder().amount(new BigDecimal(1500)).chequeNo("UISH28378298201").build();
		final Cheque chequePayment3 = Cheque.builder().amount(new BigDecimal(100)).chequeNo("IOI28378298201").build();
		chequeRepository.save(chequePayment1);
		chequeRepository.save(chequePayment2);
		chequeRepository.save(chequePayment3);
		final List<Payment> paymentList = chequeRepository.findByAmountBetween(new BigDecimal(200), new BigDecimal(2000));
		assertThat(paymentList).hasOnlyElementsOfType(Cheque.class);
		assertThat(paymentList).hasSize(2).extracting(Payment::getAmount).anyMatch(amount -> amount.equals(new BigDecimal(1500)));
	}

}
