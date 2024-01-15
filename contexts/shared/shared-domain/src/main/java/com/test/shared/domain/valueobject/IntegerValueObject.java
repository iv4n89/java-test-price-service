package com.test.shared.domain.valueobject;

public abstract class IntegerValueObject extends ValueObject<Integer> {

  protected IntegerValueObject(Integer value) {
    super(value);
  }

  @Override
  protected void isValid() {
    isNotNull();
  }

  protected void isNotNull() {
    if (value == null) {
      throw new IllegalArgumentException("The value cannot be null");
    }
  }
}
