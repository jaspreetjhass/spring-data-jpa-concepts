package org.jp.spring.data.jpa.association.mapping.nonentity.repository;

import java.util.List;

import org.jp.spring.data.jpa.association.mapping.nonentity.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findByName(String name);

	void deleteByName(String name);
	
}
