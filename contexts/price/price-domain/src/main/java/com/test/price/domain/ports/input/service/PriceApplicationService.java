package com.test.price.domain.ports.input.service;

import com.test.price.domain.model.PriceModel;

import java.time.LocalDateTime;

public interface PriceApplicationService {
  PriceModel findPrice(Long brandId, Long productId, LocalDateTime date);
}
