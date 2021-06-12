package org.jp.spring.data.jpa.inheritance.mapping;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.jp.spring.data.jpa.inheritance.mapping.starter.InheritanceMappingApplication;
import org.jp.spring.data.jpa.inheritance.mapping.starter.entity.Address;
import org.jp.spring.data.jpa.inheritance.mapping.starter.entity.HomeAddress;
import org.jp.spring.data.jpa.inheritance.mapping.starter.entity.OfficeAddress;
import org.jp.spring.data.jpa.inheritance.mapping.starter.repositories.AddressRepository;
import org.jp.spring.data.jpa.inheritance.mapping.starter.repositories.HomeAddressRepository;
import org.jp.spring.data.jpa.inheritance.mapping.starter.repositories.OfficeAddressRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = InheritanceMappingApplication.class)
@DataJpaTest(bootstrapMode = BootstrapMode.DEFAULT)
public class AddressRepositoryTest {

	@Autowired
	private HomeAddressRepository homeAddressRepository;
	@Autowired
	private OfficeAddressRepository officeAddressRepository;
	@Autowired
	private AddressRepository<Address> addressRepository;

	@Test
	public void findAllAddressByStatePattern() {
		final HomeAddress homeAddress = HomeAddress.builder().state("haryana").city("sonipat").apartmentNo("ap01")
				.build();
		final OfficeAddress officeAddress = OfficeAddress.builder().state("haryana").city("gurgaon")
				.buildingNo("build01").build();

		addressRepository.save(homeAddress);
		addressRepository.save(officeAddress);

		final List<Address> addressList = addressRepository.findByStateLike("%ryana%");
		addressList.forEach(System.out::println);
	}

	@Test
	public void findByStateLike() {
		final HomeAddress homeAddress = HomeAddress.builder().state("haryana").city("sonipat").apartmentNo("ap01")
				.build();
		final OfficeAddress officeAddress = OfficeAddress.builder().state("haryana").city("gurgaon")
				.buildingNo("build01").build();

		homeAddressRepository.save(homeAddress);
		officeAddressRepository.save(officeAddress);

		final List<Address> addressList = addressRepository.findByStateLike("%ana%");
		assertThat(addressList).hasSize(2);
	}

	@Test
	public void findByCityIn() {
		final HomeAddress homeAddress1 = HomeAddress.builder().state("haryana").city("sonipat").apartmentNo("ap01")
				.build();
		final HomeAddress homeAddress2 = HomeAddress.builder().state("tamil nadu").city("chennai").apartmentNo("ap01")
				.build();
		final HomeAddress homeAddress3 = HomeAddress.builder().state("haryana").city("chandigarh").apartmentNo("ap01")
				.build();

		final OfficeAddress officeAddress = OfficeAddress.builder().state("haryana").city("gurgaon")
				.buildingNo("build01").build();

		homeAddressRepository.saveAll(Arrays.asList(homeAddress1, homeAddress2, homeAddress3));
		officeAddressRepository.save(officeAddress);

		final List<Address> addressList = addressRepository.findByCityIn(Arrays.asList("new delhi", "chennai"));
		assertThat(addressList).isNotEmpty().hasSize(1).map(Address::getState)
				.allMatch(state -> state.equals("tamil nadu"));

	}

	@Test
	public void findByApartmentNo() {
		final HomeAddress homeAddress1 = HomeAddress.builder().state("haryana").city("sonipat").apartmentNo("ap01")
				.build();
		final HomeAddress homeAddress2 = HomeAddress.builder().state("tamil nadu").city("chennai").apartmentNo("bh01")
				.build();

		homeAddressRepository.saveAll(Arrays.asList(homeAddress1, homeAddress2));

		final List<HomeAddress> homeAddressList = homeAddressRepository.findByApartmentNo("ap01");
		assertThat(homeAddressList).isNotEmpty().hasSize(1).map(Address::getState)
				.allMatch(state -> state.equals("haryana"));

	}

	@Test
	public void findByBuildingNo() {
		final OfficeAddress officeAddress1 = OfficeAddress.builder().state("haryana").city("chandigarh")
				.buildingNo("build01").build();
		final OfficeAddress officeAddress2 = OfficeAddress.builder().state("delhi").city("new delhi")
				.buildingNo("sdj01").build();
		final OfficeAddress officeAddress3 = OfficeAddress.builder().state("jharkhand").city("ranchi")
				.buildingNo("seo01").build();
		final OfficeAddress officeAddress4 = OfficeAddress.builder().state("tamil nadu").city("coimbatore")
				.buildingNo("uir01").build();
		final OfficeAddress officeAddress5 = OfficeAddress.builder().state("west bengal").city("kolkata")
				.buildingNo("tui01").build();

		officeAddressRepository
				.saveAll(Arrays.asList(officeAddress1, officeAddress2, officeAddress3, officeAddress4, officeAddress5));

		final List<OfficeAddress> officeAddressList = officeAddressRepository.findByBuildingNo("seo01");
		assertThat(officeAddressList).isNotEmpty().hasSize(1).map(Address::getState)
				.allMatch(state -> state.equals("jharkhand"));

	}

}
