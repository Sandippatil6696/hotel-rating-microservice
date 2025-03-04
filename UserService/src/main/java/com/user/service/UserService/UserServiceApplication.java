package com.user.service.UserService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients  //for microservice communication
public class UserServiceApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
