//package com.bob.candidatedetails;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.cloud.openfeign.EnableFeignClients;
//import java.util.TimeZone;
//
//@SpringBootApplication
//@EnableFeignClients
//public class CandidateDetailsApplication extends SpringBootServletInitializer {
//
//	public static void main(String[] args) {
//		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata")); // Set default timezone to Asia/Kolkata
//		SpringApplication.run(CandidateDetailsApplication.class, args);
//	}
//
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(CandidateDetailsApplication.class);
//	}
//}
package com.bob.candidatedetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.TimeZone;

@SpringBootApplication
@EnableFeignClients
public class CandidateDetailsApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata")); // Set default timezone to Asia/Kolkata
        SpringApplication.run(CandidateDetailsApplication.class, args);
    }

}