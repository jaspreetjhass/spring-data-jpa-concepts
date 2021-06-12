package org.jp.spring.data.jpa.association.mapping.repository;

import java.util.List;

import org.jp.spring.data.jpa.association.mapping.entity.manytoone.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	List<Company> findByCompanyName(String companyName);

	List<Company> findByCategoryIn(List<String> categoryList);
	
	void deleteByCompanyName(String companyName);

}
