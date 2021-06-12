package org.jp.spring.data.jpa.component.mapping.entity;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Payment {

	@EmbeddedId
	private PaymentId paymentId;
	private double amount;
	@Temporal(TemporalType.TIMESTAMP)
	private Date transactionTime;

}
