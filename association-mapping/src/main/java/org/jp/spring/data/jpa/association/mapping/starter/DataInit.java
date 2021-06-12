package org.jp.spring.data.jpa.association.mapping.starter;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.jp.spring.data.jpa.association.mapping.entity.manytoone.Company;
import org.jp.spring.data.jpa.association.mapping.entity.manytoone.Employer;
import org.jp.spring.data.jpa.association.mapping.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Transactional
@Component
public class DataInit implements CommandLineRunner {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public void run(String... args) throws Exception {
		saveEmployeeRecordWithCompanyDetail();
		final List<Employer> empNameList = employeeRepository.findByEmpNameLike("%mich%");
		Employer employer = empNameList.get(0);
		final Company company = Company.builder().companyName("facebook").category("IT").employer(employer).build();
		employer.getCompanyList().add(company);
		
		employeeRepository.save(employer);
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
