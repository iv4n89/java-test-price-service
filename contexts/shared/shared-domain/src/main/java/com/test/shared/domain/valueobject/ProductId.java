package com.test.shared.domain.valueobject;

public final class ProductId extends PositiveNumericValueObject<Long> {

  public ProductId(Long value) {
    super(value);
  }

  @Override
  protected String getNullValueErrorMessage() {
    return "ProductId value cannot be null";
  }

  @Override
  protected String getNegativeValueErrorMessage() {
    return "ProductId value cannot be negative";
  }

}
