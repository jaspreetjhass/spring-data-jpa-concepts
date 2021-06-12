package org.jp.spring.data.jpa.component.mapping.entity;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Embeddable
public class Address {

	private String streetAddress;
	private String city;
	private String state;
	private String pincode;
	
}
