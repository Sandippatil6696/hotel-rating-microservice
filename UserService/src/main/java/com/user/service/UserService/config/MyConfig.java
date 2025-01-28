package com.user.service.UserService.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {

    // created for microservice communication 
    @LoadBalanced
    @Bean
		public RestTemplate restTemplate(){
			return new RestTemplate();
		}


}
