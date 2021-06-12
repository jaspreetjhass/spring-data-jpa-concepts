package org.jp.spring.data.jpa.association.mapping.nonentity.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "org.jp.spring.data.jpa.association.mapping.nonentity.repository" })
@EntityScan(basePackages = { "org.jp.spring.data.jpa.association.mapping.nonentity.entity" })
@SpringBootApplication
public class AssociationMappingNonEntityApp {

	public static void main(String[] args) {
		SpringApplication.run(AssociationMappingNonEntityApp.class, args);
	}

}
