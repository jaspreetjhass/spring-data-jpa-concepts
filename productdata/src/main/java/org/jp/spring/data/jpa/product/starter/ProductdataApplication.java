package org.jp.spring.data.jpa.product.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "org.jp.spring.data.jpa.product.repositories" })
@EntityScan(basePackages = { "org.jp.spring.data.jpa.product.entity" })
@SpringBootApplication
public class ProductdataApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductdataApplication.class, args);
	}

}
