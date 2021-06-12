package org.jp.spring.data.jpa.component.mapping.repository;

import java.util.Arrays;
import java.util.List;

import org.jp.spring.data.jpa.association.mapping.entity.manytoone.Company;
import org.jp.spring.data.jpa.association.mapping.entity.manytoone.Employer;
import org.jp.spring.data.jpa.association.mapping.repository.EmployeeRepository;
import org.jp.spring.data.jpa.association.mapping.starter.AssociationMappingApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest(bootstrapMode = BootstrapMode.LAZY)
@ContextConfiguration(classes = AssociationMappingApp.class)
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	public void findEmployeeByNameLike() {
		saveEmployeeRecordWithCompanyDetail();
		final List<Employer> empNameList = employeeRepository.findByEmpNameLike("%mich%");
		empNameList.forEach(employee -> {
			System.out.println(employee.getCompanyList());
		});
		
	}
	
	@Test
	public void employeeTransfer() {
		saveEmployeeRecordWithCompanyDetail();
		final List<Employer> empNameList = employeeRepository.findByEmpNameLike("%mich%");
		Employer employer = empNameList.get(0);
		final Company company = Company.builder().companyName("facebook").category("IT").employer(employer).build();
		employer.getCompanyList().add(company);
		
		employeeRepository.save(employer);
	}
	
	
	@Test
	public void deleteEmployeeById() {
		saveEmployeeRecordWithCompanyDetail();
		employeeRepository.deleteById(1l);
		employeeRepository.findAll().forEach(System.out::println);
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

		employeeRepository.saveAll(Arrays.asList(employer1, employer2, employer3));
	}

}
