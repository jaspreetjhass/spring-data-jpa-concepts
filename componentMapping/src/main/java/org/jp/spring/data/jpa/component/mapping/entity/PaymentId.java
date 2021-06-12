package org.jp.spring.data.jpa.component.mapping.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Embeddable
public class PaymentId implements Serializable {

	private static final long serialVersionUID = -5260779087726277968L;
	private int id;
	private String pType;

}
