package org.jp.spring.data.jpa.association.mapping.repository;

import java.util.List;

import org.jp.spring.data.jpa.association.mapping.entity.onetomany.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findByName(String name);
	
	@Modifying
	@Query(value = "delete from Customer where name=:username")
	void deleteByName(@Param("username") String name);

}
