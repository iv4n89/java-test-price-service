package com.test.price.domain.ports.input.usecases;

import com.test.price.domain.model.PriceModel;

import java.time.LocalDateTime;

public interface PriceFinder {
  PriceModel findPrice(Long brandId, Long productId, LocalDateTime date);
}
