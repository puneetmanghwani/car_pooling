package com.demo.car_pooling;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableScheduling
@EnableAsync
@EnableEurekaClient
//@EnableFeignClients
public class CarPoolingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarPoolingApplication.class, args);
	}

}
