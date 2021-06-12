package org.jp.spring.data.jpa.component.mapping.repository;

import java.util.List;

import org.jp.spring.data.jpa.component.mapping.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findByAddressStreetAddressLike(String streetAddress);
	
	void deleteByAddressCityIn(List<String> cities);

}
