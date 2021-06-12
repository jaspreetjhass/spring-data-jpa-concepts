package org.jp.spring.data.jpa.association.mapping.entity.manytoone;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Employer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	private String empName;
	private double salary;
	@ToString.Exclude
	@OneToMany(mappedBy = "employer",cascade = CascadeType.ALL)
	private List<Company> companyList;

	public void addCompanyDetails(final Company company) {
		if (companyList == null) {
			companyList = new ArrayList<Company>();
		}
		company.setEmployer(this);
		companyList.add(company);
	}

}
