package com.test.price.domain;

import com.test.price.domain.valueobject.Priority;

public final class PriorityMother {
  public static Priority create(Integer value) {
    return new Priority(value);
  }

  public static Priority random() {
    final int[] possibleValues = {0, 1, 2, 3};
    return new Priority(possibleValues[(int) (Math.random() * possibleValues.length)]);
  }
}
