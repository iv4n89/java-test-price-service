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

  /**
   * Finds the price for a given brand, product and date.
   * If there are more than one price, we will return the one with the highest priority
   *
   * @param brandId brand identifier
   * @param productId product identifier
   * @param date date to when we want to find the price
   * @return the price for the given brand, product and date
   */
  @Override
  public PriceModel findPrice(Long brandId, Long productId, LocalDateTime date) {
    return priceFinder.findPrice(brandId, productId, date);
  }
}
