package com.example.CandidateDetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.TimeZone;

@SpringBootApplication
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
//package com.example.CandidateDetails;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import java.util.TimeZone;
//
//@SpringBootApplication
//public class CandidateDetailsApplication {
//
//	public static void main(String[] args) {
//		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata")); // Set default timezone to Asia/Kolkata
//		SpringApplication.run(CandidateDetailsApplication.class, args);
//	}
//
//}