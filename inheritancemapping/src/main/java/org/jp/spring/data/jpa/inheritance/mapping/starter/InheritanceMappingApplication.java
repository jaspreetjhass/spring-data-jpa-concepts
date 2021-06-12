package org.jp.spring.data.jpa.inheritance.mapping.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "org.jp.spring.data.jpa.inheritance.mapping.repositories" })
@EntityScan(basePackages = { "org.jp.spring.data.jpa.inheritance.mapping.entity" })
@SpringBootApplication
public class InheritanceMappingApplication {

	public static void main(String[] args) {
		SpringApplication.run(InheritanceMappingApplication.class, args);
	}

}
