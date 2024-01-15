package com.test.price.application.adapters.input.service;

import com.test.price.domain.model.PriceModel;
import com.test.price.domain.ports.input.service.PriceApplicationService;
import com.test.price.domain.ports.input.usecases.PriceFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Primary
@Service("priceApplicationServiceImpl")
public final class PriceApplicationServiceImpl implements PriceApplicationService {

  private final PriceFinder priceFinder;

  @Override
  public PriceModel findPrice(Long brandId, Long productId, LocalDateTime date) {
    return priceFinder.findPrice(brandId, productId, date);
  }
}
