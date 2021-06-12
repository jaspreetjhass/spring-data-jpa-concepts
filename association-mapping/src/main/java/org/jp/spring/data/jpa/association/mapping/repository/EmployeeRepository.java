package org.jp.spring.data.jpa.association.mapping.repository;

import java.util.List;

import org.jp.spring.data.jpa.association.mapping.entity.manytoone.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employer, Long> {

	List<Employer> findBySalaryBetween(double startRange, double endRange);

	List<Employer> findByEmpNameLike(String empName);

}
