package org.jp.spring.data.jpa.association.mapping.repository;

import org.jp.spring.data.jpa.association.mapping.entity.onetomany.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {

}
