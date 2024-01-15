package com.test.price.application;

import com.test.price.application.adapters.input.service.PriceApplicationServiceImpl;
import com.test.price.application.adapters.input.usecases.PriceFinderImpl;
import com.test.price.domain.ports.input.service.PriceApplicationService;
import com.test.price.domain.ports.input.usecases.PriceFinder;
import com.test.price.domain.ports.output.repository.PriceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;

@SpringBootApplication(scanBasePackages = "com.test.price")
public class PriceApplicationTestConfig {

    @Bean(name = "priceRepositoryTest")
    public PriceRepository priceRepository() {
        return mock(PriceRepository.class);
    }

    @Bean(name = "priceFinderTest")
    public PriceFinder priceFinder() {
        return new PriceFinderImpl(priceRepository());
    }

    @Bean(name = "priceApplicationServiceTest")
    public PriceApplicationService priceApplicationService() {
        return new PriceApplicationServiceImpl(priceFinder());
    }

}
