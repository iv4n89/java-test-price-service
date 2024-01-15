package com.test.price.infrastructure.adapters.output.repository;

import com.test.price.application.exception.PriceNotFoundException;
import com.test.price.domain.model.PriceModel;
import com.test.price.domain.ports.output.repository.PriceRepository;
import com.test.price.infrastructure.mapper.PriceDataMapper;
import com.test.price.infrastructure.repository.PriceJpaRepository;
import com.test.shared.domain.valueobject.BrandId;
import com.test.shared.domain.valueobject.ProductId;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Primary
@Component("priceRepository")
public final class H2PriceRepository implements PriceRepository {
    private final PriceJpaRepository priceJpaRepository;

    /**
     * Find price by brand identifier, product identifier and application date
     * If there are more than one price, we will return the one with the highest priority
     *
     * @param brandId brand identifier
     * @param productId product identifier
     * @param applicationDate date when we want to find the price
     * @return price found
     */
    @Override
    public PriceModel findPriceByBrandIdAndProductIdAndApplicationDate(BrandId brandId, ProductId productId, LocalDateTime applicationDate) {
        return priceJpaRepository
                .findByBrandIdAndProductIdAndStartDate(brandId.getValue(), productId.getValue(), applicationDate)
                .map(PriceDataMapper::toDomainModel)
                .orElseThrow(() -> new PriceNotFoundException("Price not found"));
    }
}
