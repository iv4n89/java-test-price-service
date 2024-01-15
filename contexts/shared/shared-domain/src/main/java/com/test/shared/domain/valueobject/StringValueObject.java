package com.test.shared.domain.valueobject;

public class StringValueObject extends ValueObject<String> {

  public StringValueObject(String value) {
    super(value);
  }

  @Override
  protected void isValid() {
    isNotNull();
    isNotEmpty();
  }

  protected void isNotNull() {
    if (value == null) {
      throw new IllegalArgumentException("The value cannot be null");
    }
  }

  protected void isNotEmpty() {
    if (value.isEmpty()) {
      throw new IllegalArgumentException("The value cannot be empty");
    }
  }
}
