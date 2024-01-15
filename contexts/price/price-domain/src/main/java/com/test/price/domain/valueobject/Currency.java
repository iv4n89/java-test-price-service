package com.test.price.domain.valueobject;

import com.test.shared.domain.valueobject.StringValueObject;

public final class Currency extends StringValueObject {
  public Currency(String value) {
    super(value);
  }

  @Override
  protected void isValid() {
    super.isValid();
    hasNotThreeChars();
  }

  @Override
  protected void isNotNull() {
    if (value == null) {
      throw new IllegalArgumentException("Currency value cannot be null");
    }
  }

  @Override
  protected void isNotEmpty() {
    if (value.isEmpty()) {
      throw new IllegalArgumentException("Currency value cannot be empty");
    }
  }

  private void hasNotThreeChars() {
    if (value.length() != 3) {
      throw new IllegalArgumentException("Currency value needs to have three characters");
    }
  }
}
