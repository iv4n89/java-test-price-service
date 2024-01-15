package com.test.price.domain;

import com.test.price.domain.valueobject.PriceList;
import com.test.shared.domain.valueobject.MotherCreator;

public final class PriceListMother {
    public static PriceList create(Long value) {
        return new PriceList(value);
    }

    public static PriceList random() {
        return new PriceList(MotherCreator.random().number().randomNumber());
    }
}
