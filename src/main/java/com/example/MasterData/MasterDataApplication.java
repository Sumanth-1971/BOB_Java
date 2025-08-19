//package com.example.MasterData;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//
//import java.util.TimeZone;
//
//@SpringBootApplication
//public class MasterDataApplication extends SpringBootServletInitializer {
//
//	public static void main(String[] args) {
//		// Set default timezone
//		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
//		SpringApplication.run(MasterDataApplication.class, args);
//	}
//
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(MasterDataApplication.class);
//	}
//}

package com.example.MasterData;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class MasterDataApplication {

	public static void main(String[] args) {

		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		SpringApplication.run(MasterDataApplication.class, args);
	}

}
