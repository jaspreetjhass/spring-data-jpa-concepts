package org.jp.spring.data.jpa.inheritance.mapping.starter.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@DiscriminatorValue(value = "cc")
public class CreditCard extends Payment {

	@Column(name="credit_card_number")
	private BigInteger creditCardNo;
	
}
