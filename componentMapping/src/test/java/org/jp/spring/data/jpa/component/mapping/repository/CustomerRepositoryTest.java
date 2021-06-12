package org.jp.spring.data.jpa.component.mapping.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.jp.spring.data.jpa.component.mapping.entity.Address;
import org.jp.spring.data.jpa.component.mapping.entity.Customer;
import org.jp.spring.data.jpa.component.mapping.starter.ComponentMappingApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest(bootstrapMode = BootstrapMode.LAZY)
@ContextConfiguration(classes = ComponentMappingApp.class)
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void saveCustomer() {
		final Address address = Address.builder().streetAddress("lawrence colony").state("delhi").city("new delhi")
				.pincode("1102323").build();
		final Customer customer = Customer.builder().name("tom").address(address).build();
		final Customer customerOutput = customerRepository.save(customer);

		assertThat(customerOutput).extracting(Customer::getAddress).extracting(Address::getStreetAddress)
				.isEqualTo("lawrence colony");

	}

	@Test
	public void findCustomerByStreetAddressLike() {
		final Address address1 = Address.builder().streetAddress("lawrence colony").state("delhi").city("new delhi")
				.pincode("1102323").build();
		final Customer customer1 = Customer.builder().name("tom").address(address1).build();

		final Address address2 = Address.builder().streetAddress("karapakkam").state("tamil nadu").city("chennai")
				.pincode("1102323").build();
		final Customer customer2 = Customer.builder().name("tom").address(address2).build();

		customerRepository.saveAll(Arrays.asList(customer1, customer2));

		final List<Customer> customerList = customerRepository.findByAddressStreetAddressLike("%colony%");

		assertThat(customerList).hasSize(1).extracting(Customer::getAddress)
				.allMatch(address -> address.getStreetAddress().contains("colony"));

	}
	
	@Test
	public void updateCustomerName() {
		final Address address1 = Address.builder().streetAddress("lawrence colony").state("delhi").city("new delhi")
				.pincode("1102323").build();
		final Customer customer1 = Customer.builder().name("tom").address(address1).build();

		final Address address2 = Address.builder().streetAddress("karapakkam").state("tamil nadu").city("chennai")
				.pincode("1102323").build();
		final Customer customer2 = Customer.builder().name("tom").address(address2).build();

		customerRepository.saveAll(Arrays.asList(customer1, customer2));

		customer1.setName("garry");
		customerRepository.save(customer1);
		
		final List<Customer> customerList = customerRepository.findAll();
		assertThat(customerList).hasSize(2)
				.anyMatch(customer -> customer.getName().equals("garry"));

	}

	@Test
	public void deletCustomerCityIn() {
		final Address address1 = Address.builder().streetAddress("lawrence colony").state("delhi").city("new delhi")
				.pincode("1102323").build();
		final Customer customer1 = Customer.builder().name("tom").address(address1).build();

		final Address address2 = Address.builder().streetAddress("karapakkam").state("tamil nadu").city("chennai")
				.pincode("1102323").build();
		final Customer customer2 = Customer.builder().name("tom").address(address2).build();

		customerRepository.saveAll(Arrays.asList(customer1, customer2));

		customerRepository.deleteByAddressCityIn(Arrays.asList("chennai"));

		final List<Customer> customerList = customerRepository.findAll();
		assertThat(customerList).hasSize(1).extracting(Customer::getAddress)
				.allMatch(address -> address.getCity().equals("new delhi"));

	}

}
