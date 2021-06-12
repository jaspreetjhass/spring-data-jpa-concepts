package org.jp.spring.data.jpa.inheritance.mapping.starter.repositories;

import java.util.List;

import org.jp.spring.data.jpa.inheritance.mapping.starter.entity.HomeAddress;

public interface HomeAddressRepository extends AddressRepository<HomeAddress> {

	List<HomeAddress> findByApartmentNo(String apartmentNo);
	
}
