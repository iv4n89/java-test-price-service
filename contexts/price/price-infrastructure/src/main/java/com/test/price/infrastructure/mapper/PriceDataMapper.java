package com.test.price.infrastructure.mapper;

import com.test.price.domain.model.PriceModel;
import com.test.price.domain.valueobject.Currency;
import com.test.price.domain.valueobject.PriceList;
import com.test.price.domain.valueobject.Priority;
import com.test.price.infrastructure.entity.PriceEntity;
import com.test.shared.domain.valueobject.BrandId;
import com.test.shared.domain.valueobject.Money;
import com.test.shared.domain.valueobject.ProductId;

public final class PriceDataMapper {
  private PriceDataMapper() {}

  /**
   * Convert PriceEntity to PriceModel
   *
   * @param priceEntity PriceEntity to convert
   * @return PriceModel converted
   */
  public static PriceModel toDomainModel(PriceEntity priceEntity) {
    return PriceModel.builder()
        .brandId(new BrandId(priceEntity.getBrandId()))
        .productId(new ProductId(priceEntity.getProductId()))
        .startDate(priceEntity.getStartDate())
        .endDate(priceEntity.getEndDate())
        .priceList(new PriceList(priceEntity.getPriceList()))
        .price(new Money(priceEntity.getPrice()))
        .currency(new Currency(priceEntity.getCurr()))
        .priority(new Priority(priceEntity.getPriority()))
        .build();
  }

  /**
   * Convert PriceModel to PriceEntity
   *
   * @param priceModel PriceModel to convert
   * @return PriceEntity converted
   */
  public static PriceEntity toEntity(PriceModel priceModel) {
    return PriceEntity.builder()
        .brandId(priceModel.getBrandId().getValue())
        .productId(priceModel.getProductId().getValue())
        .startDate(priceModel.getStartDate())
        .endDate(priceModel.getEndDate())
        .priceList(priceModel.getPriceList().getValue())
        .price(priceModel.getPrice().getValue())
        .curr(priceModel.getCurrency().getValue())
        .priority(priceModel.getPriority().getValue())
        .build();
  }
}
