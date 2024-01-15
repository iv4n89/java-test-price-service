package com.test.price.domain.valueobject;

import com.test.price.domain.PriceDomainTestConfig;
import com.test.price.domain.PriceListMother;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PriceDomainTestConfig.class)
class PriceListTest {
    @Test
    void testPriceListShouldNotNull() {
        // Given
        PriceList priceList = PriceListMother.random();

        // Then
        assertNotNull(priceList);
    }

    @Test
    void testPriceListShouldThrowExceptionWhenValueIsNull() {
        // Given
        // PriceList value will be null

        // When
        IllegalArgumentException illegalArgumentException =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> {
                            PriceList priceList = new PriceList(null);
                        });

        // Then
        assertEquals("PriceList value cannot be null", illegalArgumentException.getMessage());
    }

    @Test
    void testPriceListShouldThrowExceptionWhenValueIsNegative() {
        // Given
        Long value = -1L;

        // When
        IllegalArgumentException illegalArgumentException =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> {
                            PriceList priceList = new PriceList(value);
                        });

        // Then
        assertEquals("PriceList value cannot be negative", illegalArgumentException.getMessage());
    }
}
