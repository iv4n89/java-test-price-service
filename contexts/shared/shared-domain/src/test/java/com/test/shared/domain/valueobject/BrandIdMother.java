package com.test.shared.domain.valueobject;

public final class BrandIdMother {
    public static BrandId create(Long value) {
        return new BrandId(value);
    }

    public static BrandId random() {
        return create(MotherCreator.random().number().randomNumber());
    }
}
