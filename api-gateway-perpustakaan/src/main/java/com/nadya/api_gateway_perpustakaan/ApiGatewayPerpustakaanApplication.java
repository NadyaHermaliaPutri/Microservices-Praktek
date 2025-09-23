package com.nadya.api_gateway_perpustakaan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayPerpustakaanApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayPerpustakaanApplication.class, args);
	}

}
