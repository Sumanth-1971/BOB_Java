//package com.bob.JobCreation;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//
//import java.util.TimeZone;
//
//@SpringBootApplication
//public class JobCreationApplication extends SpringBootServletInitializer {
//
//	public static void main(String[] args) {
//		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
//		SpringApplication.run(JobCreationApplication.class, args);
//	}
//
//	@Override
package com.bob.JobCreation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@SpringBootApplication
public class JobCreationApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
        SpringApplication.run(JobCreationApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        // Tells the WAR deployment how to load the Spring Boot application
        return application.sources(JobCreationApplication.class);
    }
}