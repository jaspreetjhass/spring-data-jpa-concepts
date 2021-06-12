package org.jp.spring.data.jpa.component.mapping.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.jp.spring.data.jpa.association.mapping.entity.onetomany.Customer;
import org.jp.spring.data.jpa.association.mapping.entity.onetomany.PhoneNumber;
import org.jp.spring.data.jpa.association.mapping.repository.CustomerRepository;
import org.jp.spring.data.jpa.association.mapping.repository.PhoneNumberRepository;
import org.jp.spring.data.jpa.association.mapping.starter.AssociationMappingApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest(bootstrapMode = BootstrapMode.LAZY)
@ContextConfiguration(classes = AssociationMappingApp.class)
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private PhoneNumberRepository phoneNumberRepository;

	@Test
	public void saveCustomerRecord() {

		final PhoneNumber phoneNumber1 = PhoneNumber.builder().number("3847928392832").build();
		final PhoneNumber phoneNumber2 = PhoneNumber.builder().number("9047928392832").build();
		final PhoneNumber phoneNumber3 = PhoneNumber.builder().number("304304392832").build();

		final Customer customer = Customer.builder().name("obama")
				.phoneNumberList(Arrays.asList(phoneNumber1, phoneNumber2, phoneNumber3)).build();

		customerRepository.save(customer);

		final List<Customer> customerList = customerRepository.findByName("obama");

		customerList.forEach(System.out::println);

		assertThat(customerList).hasSize(1).map(Customer::getName).contains("obama");
		assertThat(customerList.get(0).getPhoneNumberList()).hasSize(3);
	}

	@Test
	public void findCustomerByName() {

		final List<Customer> customerList = customerRepository.findByName("tom");

		customerList.forEach(System.out::println);

		assertThat(customerList).hasSize(1).map(Customer::getName).contains("tom");
		assertThat(customerList.get(0).getPhoneNumberList()).hasSize(1);
	}

	@Test
	public void findAllCustomer() {

		final List<Customer> customerList = customerRepository.findAll();

		customerList.forEach(System.out::println);

		assertThat(customerList).hasSize(5);
	}

	@Test
	public void DeleteCustomerByName() {

		customerRepository.deleteByName("garry");
		customerRepository.deleteByName("soniya");

		final List<Customer> customerList = customerRepository.findAll();

		customerList.forEach(System.out::println);

		phoneNumberRepository.findAll().forEach(System.out::println);

		assertThat(customerList).hasSize(3);
	}

	@Test
	public void updateCustomerContactList() {

		List<Customer> customerList = customerRepository.findByName("cust01");
		final Customer customer = customerList.get(0);
		final List<PhoneNumber> phoneNumberList = customer.getPhoneNumberList();
		final PhoneNumber phoneNumber1 = PhoneNumber.builder().number("3847928392832").build();
		
		final List<PhoneNumber> newPhoneNumberList = phoneNumberList.stream().filter(pnl -> pnl.getId() > 2)
				.collect(Collectors.toList());
		newPhoneNumberList.add(phoneNumber1);
		customer.setPhoneNumberList(newPhoneNumberList);

		customerRepository.save(customer);
		
		customerList = customerRepository.findByName("cust01");
		
		assertThat(customerList.get(0).getPhoneNumberList()).hasSize(4);
	}

}
