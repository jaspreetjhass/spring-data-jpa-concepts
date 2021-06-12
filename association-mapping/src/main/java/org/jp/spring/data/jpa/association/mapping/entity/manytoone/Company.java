package org.jp.spring.data.jpa.association.mapping.entity.manytoone;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long companyId;
	private String companyName;
	private String category;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employer_id", referencedColumnName = "id")
	private Employer employer;
	
}
