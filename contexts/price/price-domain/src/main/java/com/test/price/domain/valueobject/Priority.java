package com.test.price.domain.valueobject;

import com.test.shared.domain.valueobject.PositiveNumericValueObject;

public final class Priority extends PositiveNumericValueObject<Integer> {
  public Priority(Integer value) {
    super(value);
  }

  @Override
  protected String getNullValueErrorMessage() {
    return "Priority value cannot be null";
  }

  /**
   * Returns the error message for a negative value.
   *
   * @return the error message for a negative value
   */
  protected String getNegativeValueErrorMessage() {
    return "Priority value cannot be negative";
  }
}
