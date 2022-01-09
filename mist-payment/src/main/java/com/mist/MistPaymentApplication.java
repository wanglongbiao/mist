package com.mist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MistPaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(MistPaymentApplication.class, args);
    }
}
