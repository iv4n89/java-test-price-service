package com.test.price.domain;

import com.test.price.domain.model.PriceModel;
import com.test.price.domain.valueobject.Currency;
import com.test.price.domain.valueobject.PriceList;
import com.test.price.domain.valueobject.Priority;
import com.test.shared.domain.valueobject.*;

import java.time.LocalDateTime;

public final class PriceModelMother {

  public static PriceModel from(
      BrandId brandId,
      LocalDateTime startDate,
      LocalDateTime endDate,
      PriceList priceList,
      ProductId productId,
      Priority priority,
      Money price,
      Currency currency) {
    return PriceModel.builder()
        .brandId(brandId)
        .startDate(startDate)
        .endDate(endDate)
        .priceList(priceList)
        .productId(productId)
        .priority(priority)
        .price(price)
        .currency(currency)
        .build();
  }

  public static PriceModel random() {
    return PriceModel.builder()
        .brandId(BrandIdMother.random())
        .startDate(LocalDateTime.now())
        .endDate(LocalDateTime.now().plusDays(1))
        .priceList(PriceListMother.random())
        .productId(ProductIdMother.random())
        .priority(PriorityMother.random())
        .price(MoneyMother.random())
        .currency(CurrencyMother.random())
        .build();
  }

  public static PriceModel randomNoValidDates() {
    return PriceModel.builder()
        .brandId(BrandIdMother.random())
        .startDate(LocalDateTime.now().plusDays(1))
        .endDate(LocalDateTime.now())
        .priceList(PriceListMother.random())
        .productId(ProductIdMother.random())
        .priority(PriorityMother.random())
        .price(MoneyMother.random())
        .currency(CurrencyMother.random())
        .build();
  }

  public static PriceModel randomPriorityOne() {
    return PriceModel.builder()
        .brandId(BrandIdMother.random())
        .startDate(LocalDateTime.now())
        .endDate(LocalDateTime.now().plusDays(1))
        .priceList(PriceListMother.random())
        .productId(ProductIdMother.random())
        .priority(PriorityMother.create(1))
        .price(MoneyMother.random())
        .currency(CurrencyMother.random())
        .build();
  }

  public static PriceModel randomPriorityTwo() {
    return PriceModel.builder()
        .brandId(BrandIdMother.random())
        .startDate(LocalDateTime.now())
        .endDate(LocalDateTime.now().plusDays(1))
        .priceList(PriceListMother.random())
        .productId(ProductIdMother.random())
        .priority(PriorityMother.create(2))
        .price(MoneyMother.random())
        .currency(CurrencyMother.random())
        .build();
  }
}
