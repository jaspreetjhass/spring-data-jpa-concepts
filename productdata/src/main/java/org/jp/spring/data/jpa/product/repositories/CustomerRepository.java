package org.jp.spring.data.jpa.product.repositories;

import java.util.List;

import org.jp.spring.data.jpa.product.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	List<Customer> findByName(String name, Pageable pageable);

	List<Customer> findByNameAndEmail(String name, String email, Sort sort);

	List<Customer> findByEmailLike(String emailPattern);

	List<Customer> findByIdIn(List<Integer> ids, Pageable pageable);

	@Modifying
	@Query("update Customer set email=:emailId where id=:id")
	void updateCustomerEmailById(@Param("id") int id, @Param("emailId") String email);

	@Modifying
	@Query(value = "insert into Customer(name,email) values(:name,:email)", nativeQuery = true)
	void saveCustomer(String name, String email);

	Page<Customer> findByEmailContains(String emailPattern, Pageable pageable);

}
