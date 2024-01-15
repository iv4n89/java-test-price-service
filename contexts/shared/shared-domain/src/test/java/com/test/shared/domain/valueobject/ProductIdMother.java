package com.test.shared.domain.valueobject;

public final class ProductIdMother {
  public static ProductId create(Long value) {
    return new ProductId(value);
  }

  public static ProductId random() {
    return create(MotherCreator.random().number().randomNumber());
  }
}
