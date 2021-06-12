package org.jp.spring.data.jpa.association.mapping.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "org.jp.spring.data.jpa.association.mapping.repository" })
@EntityScan(basePackages = { "org.jp.spring.data.jpa.association.mapping.entity" })
@SpringBootApplication
public class AssociationMappingApp {
	

	public static void main(String[] args) {
		SpringApplication.run(AssociationMappingApp.class, args);
	}

}
