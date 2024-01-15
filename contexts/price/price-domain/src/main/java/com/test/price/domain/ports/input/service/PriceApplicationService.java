package com.test.price.domain.ports.input.service;

import com.test.price.domain.model.PriceModel;

import java.time.LocalDateTime;

public interface PriceApplicationService {
  /**
   * Find price by brand, product and date
   * If there are more than one price, we will return the one with the highest priority
   *
   * @param brandId brand identifier
   * @param productId product identifier
   * @param date date when we want to find the price
   * @return price found
   */
  PriceModel findPrice(Long brandId, Long productId, LocalDateTime date);
}
