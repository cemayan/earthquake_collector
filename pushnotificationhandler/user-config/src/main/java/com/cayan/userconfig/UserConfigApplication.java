package com.cayan.userconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserConfigApplication.class, args);
	}

}
