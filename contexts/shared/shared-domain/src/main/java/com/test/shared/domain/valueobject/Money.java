package com.test.shared.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Money extends ValueObject<BigDecimal> {
  public Money(BigDecimal value) {
    super(value);
  }

  public Money add(Money money) {
    return new Money(value.add(money.value));
  }

  public Money add(BigDecimal value) {
    return new Money(this.value.add(value));
  }

  public Money subtract(Money money) {
    return new Money(value.subtract(money.value));
  }

  public Money subtract(BigDecimal value) {
    return new Money(this.value.subtract(value));
  }

  public Money multiply(Money money) {
    return new Money(value.multiply(money.value));
  }

  public Money multiply(BigDecimal value) {
    return new Money(this.value.multiply(value));
  }

  public Money divide(Money money) {
    return new Money(value.divide(money.value, RoundingMode.HALF_UP));
  }

  public Money divide(BigDecimal value) {
    return new Money(this.value.divide(value, RoundingMode.HALF_UP));
  }

  public boolean isGreaterThanZero() {
    return value.compareTo(BigDecimal.ZERO) > 0;
  }

  public boolean isGreaterThan(Money money) {
    return value.compareTo(money.value) > 0;
  }

  public boolean isGreaterThan(BigDecimal value) {
    return this.value.compareTo(value) > 0;
  }

  @Override
  protected void isValid() {}

  private void isNotNull() {
    if (value == null) {
      throw new IllegalArgumentException("The value of money cannot be null");
    }
  }

  private void isNotNegative() {
    if (value.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("The value of money cannot be negative");
    }
  }
}
