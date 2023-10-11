package com.example.offerclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OfferClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfferClientApplication.class, args);
    }

}
