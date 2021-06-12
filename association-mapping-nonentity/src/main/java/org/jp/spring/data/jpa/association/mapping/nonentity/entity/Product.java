package org.jp.spring.data.jpa.association.mapping.nonentity.entity;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class Product {

	private String name;
	private double amount;
	
}
