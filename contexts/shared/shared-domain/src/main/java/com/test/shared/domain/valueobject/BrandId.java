package com.test.shared.domain.valueobject;

public final class BrandId extends ValueObject<Long> {
    public BrandId(Long value) {
        super(value);
    }

    @Override
    protected void isValid() {
        isNotNull();
        isNotNegative();
    }

    private void isNotNull() {
        if (value == null) {
            throw new IllegalArgumentException("The value of brandId cannot be null");
        }
    }

    private void isNotNegative() {
        if (value < 0) {
            throw new IllegalArgumentException("The value of brandId cannot be negative");
        }
    }
}
