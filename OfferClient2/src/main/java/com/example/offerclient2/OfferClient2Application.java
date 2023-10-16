package com.example.offerclient2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OfferClient2Application {

	public static void main(String[] args) {
		SpringApplication.run(OfferClient2Application.class, args);
	}

}
