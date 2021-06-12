package org.jp.spring.data.jpa.component.mapping.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.jp.spring.data.jpa.association.mapping.nonentity.entity.Customer;
import org.jp.spring.data.jpa.association.mapping.nonentity.repository.CustomerRepository;
import org.jp.spring.data.jpa.association.mapping.nonentity.starter.AssociationMappingNonEntityApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest(bootstrapMode = BootstrapMode.LAZY)
@ContextConfiguration(classes = AssociationMappingNonEntityApp.class)
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void saveCustomer() {
		final Customer customer = Customer.builder().name("tom").phoneNumbers(Arrays.asList("12982392", "349393803"))
				.build();
		customerRepository.saveAndFlush(customer);

		assertThat(customerRepository.findAll()).hasSize(6)
				.anyMatch(c -> c.getPhoneNumbers().containsAll(Arrays.asList("12982392", "349393803")));
	}

	@Test
	public void findAllCustomers() {
		final List<Customer> customerList = customerRepository.findAll();
		customerList.forEach(System.out::println);
		assertThat(customerList).hasSize(5);
	}

	@Test
	public void findCustomerById() {
		final Optional<Customer> optionalCustomer = customerRepository.findById(1l);
		optionalCustomer.ifPresent(System.out::println);
		assertThat(optionalCustomer).isNotEmpty();
	}

	@Test
	public void findCustomerByName() {
		final List<Customer> customerList = customerRepository.findByName("tom");
		customerList.forEach(System.out::println);
		assertThat(customerList).hasSize(1);
	}

	@Test
	public void deleteCustomerById() {
		customerRepository.deleteById(1l);
		final List<Customer> customerList = customerRepository.findAll();
		customerList.forEach(System.out::println);
		assertThat(customerList).hasSize(4);
	}

	@Test
	public void deleteCustomerByName() {
		customerRepository.deleteByName("tom");
		final List<Customer> customerList = customerRepository.findAll();
		customerList.forEach(System.out::println);
		assertThat(customerList).hasSize(4);
	}

	@Test
	public void deleteAll() {
		customerRepository.deleteAll();
		final List<Customer> customerList = customerRepository.findAll();
		customerList.forEach(System.out::println);
		assertThat(customerList).hasSize(0);
	}

	@Test
	public void updateCustomerContactList() {
		Optional<Customer> optionalCustomer = customerRepository.findById(1l);
		Customer customer = optionalCustomer.get();
		// customer.setName("");
		customer.getPhoneNumbers().set(0, "");
		customerRepository.saveAndFlush(customer);

		customerRepository.findById(1l).ifPresent(System.out::println);
	}

}
