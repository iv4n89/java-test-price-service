package com.test.price.domain.ports.output.repository;

import com.test.price.domain.model.PriceModel;
import com.test.shared.domain.valueobject.BrandId;
import com.test.shared.domain.valueobject.ProductId;

import java.time.LocalDateTime;

public interface PriceRepository {

  /**
   * Find price by brandId, productId and applicationDate
   * If there are more than one price, we will return the one with the highest priority
   *
   * @param brandId brand identifier
   * @param productId product identifier
   * @param applicationDate date when we want to find the price
   * @return price found
   */
  PriceModel findPriceByBrandIdAndProductIdAndApplicationDate(
      BrandId brandId, ProductId productId, LocalDateTime applicationDate);
}
