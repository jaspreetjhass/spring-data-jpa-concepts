package org.jp.spring.data.jpa.inheritance.mapping.starter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class HomeAddress extends Address {

	@Column(name="apartment_number")
	private String apartmentNo;

}
