package com.test.price.domain.valueobject;

import com.test.shared.domain.valueobject.PositiveNumericValueObject;

public final class PriceList extends PositiveNumericValueObject<Long> {
  public PriceList(Long value) {
    super(value);
  }

  @Override
  protected String getNullValueErrorMessage() {
    return "PriceList value cannot be null";
  }

  @Override
  protected String getNegativeValueErrorMessage() {
    return "PriceList value cannot be negative";
  }
}
