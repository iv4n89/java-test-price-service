package com.test.price.domain.valueobject;

import com.test.shared.domain.valueobject.IntegerValueObject;

public final class Priority extends IntegerValueObject {
  public Priority(Integer value) {
    super(value);
  }

  @Override
  protected void isValid() {
    super.isValid();
    isNotNegative();
  }

  @Override
  protected void isNotNull() {
    if (value == null) {
      throw new IllegalArgumentException("Priority value cannot be null");
    }
  }

  private void isNotNegative() {
    if (value < 0) {
      throw new IllegalArgumentException("Priority value cannot be negative");
    }
  }
}
