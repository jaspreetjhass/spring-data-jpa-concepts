package org.jp.spring.data.jpa.component.mapping.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"org.jp.spring.data.jpa.component.mapping.repository"})
@EntityScan(basePackages = {"org.jp.spring.data.jpa.component.mapping.entity"})
@SpringBootApplication
public class ComponentMappingApp {

	public static void main(String[] args) {
		SpringApplication.run(ComponentMappingApp.class, args);
	}

}
