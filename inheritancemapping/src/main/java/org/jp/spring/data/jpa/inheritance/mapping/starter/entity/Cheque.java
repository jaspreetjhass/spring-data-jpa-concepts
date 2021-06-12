package org.jp.spring.data.jpa.inheritance.mapping.starter.entity;

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
@EqualsAndHashCode(callSuper = false)
@Entity
@DiscriminatorValue("cq")
public class Cheque extends Payment{

	@Column(name="cheque_number")
	private String chequeNo;

}
