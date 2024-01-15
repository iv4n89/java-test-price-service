package com.test.application.adapters.input.usecases;

import com.test.price.domain.model.PriceModel;
import com.test.price.domain.ports.input.usecases.PriceFinder;
import com.test.price.domain.ports.output.repository.PriceRepository;
import com.test.shared.domain.valueobject.BrandId;
import com.test.shared.domain.valueobject.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Primary
@Service("priceFinderImpl")
public final class PriceFinderImpl implements PriceFinder {

  private final PriceRepository priceRepository;

  @Override
  public PriceModel findPrice(Long brandId, Long productId, LocalDateTime date) {
    final BrandId brandIdValueObject = new BrandId(brandId);
    final ProductId productIdValueObject = new ProductId(productId);
    return priceRepository.findPriceByBrandIdAndProductIdAndApplicationDate(
        brandIdValueObject, productIdValueObject, date);
  }
}
