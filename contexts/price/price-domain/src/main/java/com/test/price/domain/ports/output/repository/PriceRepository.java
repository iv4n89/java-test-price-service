package com.test.price.domain.ports.output.repository;

import com.test.price.domain.model.PriceModel;
import com.test.shared.domain.valueobject.BrandId;
import com.test.shared.domain.valueobject.ProductId;

import java.time.LocalDateTime;

public interface PriceRepository {
  PriceModel findPriceByBrandIdAndProductIdAndApplicationDate(
      BrandId brandId, ProductId productId, LocalDateTime applicationDate);
}
