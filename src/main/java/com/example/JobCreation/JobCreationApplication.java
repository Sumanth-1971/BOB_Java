package com.example.JobCreation;
import org.springframework.boot.SpringApplication;

import java.util.TimeZone;

//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//

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
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		// Tells the WAR deployment how to load the Spring Boot application
//		return application.sources(JobCreationApplication.class);
//	}
//}



public class JobCreationApplication  {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		SpringApplication.run(JobCreationApplication.class, args);
	}

}
