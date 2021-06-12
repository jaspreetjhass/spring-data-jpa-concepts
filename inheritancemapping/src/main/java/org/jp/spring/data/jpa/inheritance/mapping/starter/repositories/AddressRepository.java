package org.jp.spring.data.jpa.inheritance.mapping.starter.repositories;

import java.util.List;

import org.jp.spring.data.jpa.inheritance.mapping.starter.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository<T extends Address> extends JpaRepository<T, Integer> {

	List<Address> findByStateLike(String statePattern);
	
	List<Address> findByCityIn(List<String> cities);

}
