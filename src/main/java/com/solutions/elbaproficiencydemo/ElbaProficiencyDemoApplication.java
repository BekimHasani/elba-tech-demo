package com.solutions.elbaproficiencydemo;

import com.solutions.elbaproficiencydemo.repository.impl.CustomBatchRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomBatchRepositoryImpl.class )
public class ElbaProficiencyDemoApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(ElbaProficiencyDemoApplication.class, args);
	}
}