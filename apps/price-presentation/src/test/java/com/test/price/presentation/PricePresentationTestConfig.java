package com.test.price.presentation;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.test.price")
@EntityScan(basePackages = "com.test.price")
@SpringBootApplication(scanBasePackages = { "com.test.price", "com.test.shared" })
public class PricePresentationTestConfig {}
