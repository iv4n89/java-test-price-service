package com.test.shared.domain.valueobject;

import java.math.BigDecimal;

public final class MoneyMother {
  public static Money create(BigDecimal value) {
    return new Money(value);
  }

  public static Money random() {
    double randomValue = Math.floor(Math.random() * 1000);
    if (randomValue < 0) {
      randomValue *= -1;
    }
    return new Money(BigDecimal.valueOf(randomValue));
  }

  public static Money zero() {
    return new Money(BigDecimal.ZERO);
  }

  public static Money one() {
    return new Money(BigDecimal.ONE);
  }
}
