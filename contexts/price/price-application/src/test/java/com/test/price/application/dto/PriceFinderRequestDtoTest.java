package com.test.price.application.dto;

import com.test.price.application.PriceApplicationTestConfig;
import com.test.shared.domain.valueobject.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = PriceApplicationTestConfig.class)
class PriceFinderRequestDtoTest {
    @Test
    void testPriceFinderRequestDtoShouldBeCreated() {
        // Given
        BrandId brandId = BrandIdMother.random();
        ProductId productId = ProductIdMother.random();
        LocalDateTime date = DateMother.randomLocalDateTime();

        // When
        PriceFinderRequestDto priceFinderRequestDto = new PriceFinderRequestDto(brandId.getValue(), productId.getValue(), date);

        // Then
        assertNotNull(priceFinderRequestDto);
        assertEquals(brandId.getValue(), priceFinderRequestDto.brandId());
        assertEquals(productId.getValue(), priceFinderRequestDto.productId());
        assertEquals(date, priceFinderRequestDto.date());
    }
}
