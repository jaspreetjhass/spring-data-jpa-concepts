package org.jp.spring.data.jpa.jpql.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "org.jp.spring.data.jpa.jpql.repositories" })
@EntityScan(basePackages = { "org.jp.spring.data.jpa.jpql.entity" })
@SpringBootApplication
public class JpqlAndNativeQueriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpqlAndNativeQueriesApplication.class, args);
	}

}
