package com.example.CandidateDetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class CandidateDetailsApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata")); // Set default timezone to Asia/Kolkata
		SpringApplication.run(CandidateDetailsApplication.class, args);
	}

}
