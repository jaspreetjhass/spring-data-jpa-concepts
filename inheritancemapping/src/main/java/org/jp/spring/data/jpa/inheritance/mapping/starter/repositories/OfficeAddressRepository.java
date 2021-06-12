package org.jp.spring.data.jpa.inheritance.mapping.starter.repositories;

import java.util.List;

import org.jp.spring.data.jpa.inheritance.mapping.starter.entity.OfficeAddress;
import org.springframework.data.jpa.repository.Query;

public interface OfficeAddressRepository extends AddressRepository<OfficeAddress> {

	@Query("from OfficeAddress where buildingNo=:buildingNo")
	List<OfficeAddress> findByBuildingNo(String buildingNo);

}
