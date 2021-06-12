package org.jp.spring.data.jpa.inheritance.relationship;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.jp.spring.data.jpa.inheritance.relationship.entity.Customer;
import org.jp.spring.data.jpa.inheritance.relationship.repositories.CustomerRepository;
import org.jp.spring.data.jpa.inheritance.relationship.starter.InheritanceRelationshipApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest(bootstrapMode = BootstrapMode.LAZY)
@ContextConfiguration(classes = InheritanceRelationshipApplication.class)
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void saveCustomer() {
		final Customer customer = Customer.builder().name("cust12").email("email12").build();
		customerRepository.save(customer);
		final Example<Customer> example = Example.of(customer);
		assertTrue(customerRepository.exists(example));
	}

	@Test
	public void fetchCustomerByName() {
		final Example<Customer> example = Example.of(Customer.builder().name("cust01").build());
		final List<Customer> customerList = customerRepository.findAll(example);
		assertThat(customerList).allMatch(cust -> cust.getEmail().equals("email01")).hasSize(1);
	}

	@Test
	public void updateCustomerEmail() {
		final Customer customer = customerRepository.findById(1).get();
		customer.setEmail("abc@gmail.com");
		final Customer outputCustomer = customerRepository.save(customer);
		assertThat(outputCustomer).isNotNull().extracting("email").isEqualTo("abc@gmail.com");
	}

	@Test
	public void deleteCustomer() {
		customerRepository.deleteById(2);
		assertThat(customerRepository.count()).isEqualTo(9);
	}

	@Test
	public void fetchCustomerByNameCustom() {
		final List<Customer> customerList = customerRepository.findByName("cust01",null);
		assertThat(customerList).allMatch(cust -> cust.getEmail().equals("email01")).hasSize(1);
	}

	@Test
	public void fetchCustomerByNameAndEmail() {
		final List<Customer> customerList = customerRepository.findByNameAndEmail("cust02", "email02",null);
		assertThat(customerList).allMatch(cust -> cust.getEmail().equals("email02")).hasSize(1);
	}

	@Test
	public void fetchCustomerByEmailLike() {
		final List<Customer> customerList = customerRepository.findByEmailLike("%mail%");
		assertThat(customerList).anyMatch(cust -> cust.getEmail().equals("email02")).hasSize(6);
	}

	@Test
	public void updateCustomerEmailById() {
		customerRepository.updateCustomerEmailById(1, "email@gmail.com");
		assertEquals("email@gmail.com", customerRepository.findById(1).get().getEmail());
	}

	@Test
	@Rollback(false)
	public void saveCustomerByJPQL() {
		customerRepository.saveCustomer("michel", "michel@gmail.com");
		assertEquals("michel@gmail.com", customerRepository.findByName("michel",null).get(0).getEmail());
	}
	
	@Test
	public void fetchCustomerByIdsPageSize2() {
		final List<Customer> customerList = customerRepository.findByIdIn(Arrays.asList(4,5,6,7),PageRequest.of(0, 2));
		customerList.forEach(System.out::println);
		assertThat(customerList).anyMatch(cust -> cust.getEmail().equals("garrysandhu@hotmail.com")).hasSize(2);
	}
	
	@Test
	public void fetchCustomerByIdPageSize5() {
		final List<Customer> customerList = customerRepository.findByIdIn(Arrays.asList(4,5,6,7,1,2,3,8,9,10),PageRequest.of(1, 5));
		customerList.forEach(System.out::println);
		assertThat(customerList).anyMatch(cust -> cust.getEmail().equals("robert@gmail.com")).hasSize(5);
	}
	
	@Test
	public void fetchCustomerByEmailContains() {
		final Page<Customer> customerPage= customerRepository.findByEmailContains("mail", PageRequest.of(1, 2, Direction.DESC, "name"));
		customerPage.forEach(System.out::println);
		assertThat(customerPage).anyMatch(cust -> cust.getEmail().equals("kartik@hotmail.com"));
	}

}
