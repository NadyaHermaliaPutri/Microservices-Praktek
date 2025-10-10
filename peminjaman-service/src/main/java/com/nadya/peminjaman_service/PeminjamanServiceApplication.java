package com.nadya.peminjaman_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableDiscoveryClient
@EnableMongoRepositories(basePackages = "com.nadya.peminjaman_service.repository")
public class PeminjamanServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeminjamanServiceApplication.class, args);
	}	
}
