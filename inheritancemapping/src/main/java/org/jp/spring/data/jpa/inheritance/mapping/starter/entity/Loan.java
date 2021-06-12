package org.jp.spring.data.jpa.inheritance.mapping.starter.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double interestRate;
	private double processingFee;

}
