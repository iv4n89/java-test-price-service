package com.test.shared.domain.valueobject;

import java.util.Objects;

public abstract class ValueObject<T> {
  protected final T value;

  protected ValueObject(T value) {
    this.value = value;
    isValid();
  }

  public T getValue() {
    return value;
  }

  protected abstract void isValid();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ValueObject<?> that = (ValueObject<?>) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
