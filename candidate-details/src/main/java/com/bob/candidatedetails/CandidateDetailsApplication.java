package com.bob.candidatedetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.util.TimeZone;

@SpringBootApplication
@EnableFeignClients
@EntityScan(basePackages = "com.bob.db.entity")
@EnableJpaRepositories(basePackages = "com.bob.db.repository")
public class CandidateDetailsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata")); // Set default timezone to Asia/Kolkata
		SpringApplication.run(CandidateDetailsApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CandidateDetailsApplication.class);
	}
}