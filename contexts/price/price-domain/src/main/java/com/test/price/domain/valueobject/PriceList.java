package com.test.price.domain.valueobject;

import com.test.shared.domain.valueobject.ValueObject;

public final class PriceList extends ValueObject<Long> {
  public PriceList(Long value) {
    super(value);
  }

  @Override
  protected void isValid() {
    isNotNull();
    isNotNegative();
  }

  private void isNotNull() {
    if (value == null) {
      throw new IllegalArgumentException("PriceList value cannot be null");
    }
  }

  private void isNotNegative() {
    if (value < 0) {
      throw new IllegalArgumentException("PriceList value cannot be negative");
    }
  }
}
