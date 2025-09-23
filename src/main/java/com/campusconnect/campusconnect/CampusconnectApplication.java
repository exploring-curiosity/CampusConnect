package com.campusconnect.campusconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.campusconnect.campusconnect", "com.campusconnect.demo"})
public class CampusconnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampusconnectApplication.class, args);
	}

}
