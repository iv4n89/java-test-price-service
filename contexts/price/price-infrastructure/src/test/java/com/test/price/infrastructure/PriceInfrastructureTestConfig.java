package com.test.price.infrastructure;

import static org.mockito.Mockito.mock;

import com.test.price.domain.ports.output.repository.PriceRepository;
import com.test.price.infrastructure.adapters.output.repository.H2PriceRepository;
import com.test.price.infrastructure.repository.PriceJpaRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.test.infrastructure")
public class PriceInfrastructureTestConfig {
    @Bean(name = "priceJpaRepositoryTest")
    public PriceJpaRepository priceJpaRepository() {
        return mock(PriceJpaRepository.class);
    }

    @Bean(name = "priceRepositoryTest")
    public PriceRepository priceRepository() {
        return new H2PriceRepository(priceJpaRepository());
    }
}
