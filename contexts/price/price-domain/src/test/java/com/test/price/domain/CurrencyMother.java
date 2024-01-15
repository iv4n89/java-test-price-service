package com.test.price.domain;

import com.test.price.domain.valueobject.Currency;
import com.test.shared.domain.valueobject.WordMother;

public final class CurrencyMother {
    public static Currency create(String value) {
        return new Currency(value);
    }

    public static Currency random() {
        return new Currency(WordMother.random(3));
    }
}
