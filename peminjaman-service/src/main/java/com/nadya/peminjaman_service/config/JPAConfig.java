package com.nadya.peminjaman_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.nadya.peminjaman_service.repository")
public class JPAConfig {
}