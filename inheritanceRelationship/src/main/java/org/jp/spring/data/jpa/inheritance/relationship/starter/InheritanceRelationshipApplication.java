package org.jp.spring.data.jpa.inheritance.relationship.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "org.jp.spring.data.jpa.inheritance.relationship.repositories" })
@EntityScan(basePackages = { "org.jp.spring.data.jpa.inheritance.relationship.entity" })
@SpringBootApplication
public class InheritanceRelationshipApplication {

	public static void main(String[] args) {
		SpringApplication.run(InheritanceRelationshipApplication.class, args);
	}

}
