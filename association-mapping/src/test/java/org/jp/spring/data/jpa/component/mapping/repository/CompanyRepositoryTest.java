package org.jp.spring.data.jpa.component.mapping.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.jp.spring.data.jpa.association.mapping.entity.manytoone.Company;
import org.jp.spring.data.jpa.association.mapping.entity.manytoone.Employer;
import org.jp.spring.data.jpa.association.mapping.repository.CompanyRepository;
import org.jp.spring.data.jpa.association.mapping.starter.AssociationMappingApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest(bootstrapMode = BootstrapMode.LAZY)
@ContextConfiguration(classes = AssociationMappingApp.class)
public class CompanyRepositoryTest {

	@Autowired
	private CompanyRepository companyRepository;

	@Test
	public void saveCompanyDetails() {
		saveEmployeeRecordWithCompanyDetail();
		final List<Company> companyList = companyRepository.findAll();

		companyList.forEach(System.out::println);
		
		assertThat(companyList).hasSize(5);
		assertThat(companyList).extracting(Company::getEmployer).anyMatch(e -> e.getEmpName().equals("garry"));

		assertTrue(companyList.stream().map(Company::getEmployer).anyMatch(emp -> emp.getEmpName().equals("michel")));
	}

	@Test
	public void findCompanyByCategory() {
		saveEmployeeRecordWithCompanyDetail();
		final List<Company> companyDetailList = companyRepository.findByCategoryIn(Arrays.asList("IT", "Finance"));
		final List<Company> itList = companyDetailList.stream().filter(company -> company.getCategory().equals("IT"))
				.collect(Collectors.toList());
		final List<Company> financeList = companyDetailList.stream()
				.filter(company -> company.getCategory().equals("Finance")).collect(Collectors.toList());

		assertThat(itList).hasSize(3);
		assertThat(financeList).hasSize(1);
	}

	@Test
	public void DeleteCompanyByName() {
		saveEmployeeRecordWithCompanyDetail();
		companyRepository.deleteByCompanyName("sapient");

		final List<Company> companyDetailList = companyRepository.findByCategoryIn(Arrays.asList("IT", "Finance"));
		final List<Company> itList = companyDetailList.stream().filter(company -> company.getCategory().equals("IT"))
				.collect(Collectors.toList());
		final List<Company> financeList = companyDetailList.stream()
				.filter(company -> company.getCategory().equals("Finance")).collect(Collectors.toList());

		assertThat(itList).hasSize(2);
		assertThat(financeList).hasSize(0);
	}

	private void saveEmployeeRecordWithCompanyDetail() {
		final Employer employer1 = Employer.builder().empName("michel").salary(899223.20).build();
		final Employer employer2 = Employer.builder().empName("garry").salary(56566.20).build();
		final Employer employer3 = Employer.builder().empName("obama").salary(987677.20).build();

		final Company company1 = Company.builder().companyName("sapient").category("IT").employer(employer1).build();
		employer1.addCompanyDetails(company1);

		final Company company2 = Company.builder().companyName("Deloitte").category("Finance").employer(employer1)
				.build();
		employer1.addCompanyDetails(company2);

		final Company company3 = Company.builder().companyName("nokia").category("Networking").employer(employer2)
				.build();
		employer2.addCompanyDetails(company3);

		final Company company4 = Company.builder().companyName("nec").category("IT").employer(employer3).build();
		employer3.addCompanyDetails(company4);

		final Company company5 = Company.builder().companyName("hcl").category("IT").employer(employer2).build();
		employer2.addCompanyDetails(company5);

		companyRepository.saveAll(Arrays.asList(company1, company2, company3, company4, company5));
	}

}
