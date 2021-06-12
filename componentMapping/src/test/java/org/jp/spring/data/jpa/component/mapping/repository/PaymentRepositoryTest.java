package org.jp.spring.data.jpa.component.mapping.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.jp.spring.data.jpa.component.mapping.entity.Payment;
import org.jp.spring.data.jpa.component.mapping.entity.PaymentId;
import org.jp.spring.data.jpa.component.mapping.starter.ComponentMappingApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest(bootstrapMode = BootstrapMode.LAZY)
@ContextConfiguration(classes = { ComponentMappingApp.class })
public class PaymentRepositoryTest {

	@Autowired
	private PaymentRepository paymentRepository;

	@Test
	public void savePayment() {
		final PaymentId paymentId = PaymentId.builder().id(1).pType("credit card").build();
		final Payment payment = Payment.builder().paymentId(paymentId).amount(1090).transactionTime(Date.from(Instant.now()))
				.build();
		paymentRepository.save(payment);
		final Optional<Payment> output = paymentRepository.findById(paymentId);
		output.ifPresent(System.out::println);
	}

	@Test
	public void findPaymentTransactionBetween() throws ParseException {
		final PaymentId paymentIdCC = PaymentId.builder().id(1).pType("cc").build();
		final PaymentId paymentIdCheque = PaymentId.builder().id(1).pType("cheque").build();

		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
		final Date ccPaymentDate = simpleDateFormat.parse("12/03/2020");
		final Date chequePaymentDate = simpleDateFormat.parse("30/03/2020");

		final Payment payment1 = Payment.builder().paymentId(paymentIdCC).amount(1090).transactionTime(ccPaymentDate).build();
		final Payment payment2 = Payment.builder().paymentId(paymentIdCheque).amount(1090).transactionTime(chequePaymentDate)
				.build();
		paymentRepository.saveAll(Arrays.asList(payment1, payment2));

		final List<Payment> paymentList = paymentRepository.findByTransactionTimeBetween(simpleDateFormat.parse("01/03/2020"),
				simpleDateFormat.parse("15/03/2020"));

		assertThat(paymentList).hasSize(1).allMatch(payment -> payment.getPaymentId().getPType().equals("cc"));
	}

}
