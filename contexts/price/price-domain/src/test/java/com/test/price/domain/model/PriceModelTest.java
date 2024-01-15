package com.test.price.domain.model;

import com.test.price.domain.*;
import com.test.price.domain.exception.PriceDomainException;
import com.test.price.domain.valueobject.Currency;
import com.test.price.domain.valueobject.PriceList;
import com.test.price.domain.valueobject.Priority;
import com.test.shared.domain.valueobject.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PriceDomainTestConfig.class)
class PriceModelTest {
    @Test
    void testPriceShouldBeCreated() {
        // Given
        BrandId brandId = BrandIdMother.random();
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);
        PriceList priceList = PriceListMother.random();
        ProductId productId = ProductIdMother.random();
        Priority priority = PriorityMother.random();
        Money price = MoneyMother.random();
        Currency currency = CurrencyMother.random();

        // When
        PriceModel expected =
                PriceModelMother.from(
                        brandId, startDate, endDate, priceList, productId, priority, price, currency);

        // Then
        assertNotNull(expected);
        assertEquals(brandId, expected.getBrandId());
        assertEquals(startDate, expected.getStartDate());
        assertEquals(endDate, expected.getEndDate());
        assertEquals(priceList, expected.getPriceList());
        assertEquals(productId, expected.getProductId());
        assertEquals(priority, expected.getPriority());
        assertEquals(price, expected.getPrice());
        assertEquals(currency, expected.getCurrency());
    }

    @Test
    void testPriceShouldThrowDomainExceptionWhenStartDateIsAfterEndDate() {
        // Given
        BrandId brandId = BrandIdMother.random();
        LocalDateTime startDate = LocalDateTime.now().plusDays(1);
        LocalDateTime endDate = LocalDateTime.now();
        PriceList priceList = PriceListMother.random();
        ProductId productId = ProductIdMother.random();
        Priority priority = PriorityMother.random();
        Money price = MoneyMother.random();
        Currency currency = CurrencyMother.random();

        // When
        PriceDomainException expected =
                assertThrows(
                        PriceDomainException.class,
                        () ->
                                PriceModelMother.from(
                                        brandId,
                                        startDate,
                                        endDate,
                                        priceList,
                                        productId,
                                        priority,
                                        price,
                                        currency
                                ));

        // Then
        assertNotNull(expected);
        assertEquals("Start date must be before end date", expected.getMessage());
    }

    @Test
    void testPriceShouldBeEqualWhenBrandIdProductIdAndStartDateAreEqual() {
        // Given
        BrandId brandId = BrandIdMother.random();
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);
        PriceList priceList = PriceListMother.random();
        ProductId productId = ProductIdMother.random();
        Priority priority = PriorityMother.random();
        Money price = MoneyMother.random();
        Currency currency = CurrencyMother.random();

        // When
        PriceModel expected =
                PriceModelMother.from(
                        brandId, startDate, endDate, priceList, productId, priority, price, currency);
        PriceModel actual =
                PriceModelMother.from(
                        brandId, startDate, endDate, priceList, productId, priority, price, currency);

        // Then
        assertNotNull(expected);
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testPriceHashCode() {
        // Given
        BrandId brandId = BrandIdMother.random();
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);
        PriceList priceList = PriceListMother.random();
        ProductId productId = ProductIdMother.random();
        Priority priority = PriorityMother.random();
        Money price = MoneyMother.random();
        Currency currency = CurrencyMother.random();

        // When
        PriceModel expected =
                PriceModelMother.from(
                        brandId, startDate, endDate, priceList, productId, priority, price, currency);
        PriceModel actual =
                PriceModelMother.from(
                        brandId, startDate, endDate, priceList, productId, priority, price, currency);

        // Then
        assertNotNull(expected);
        assertNotNull(actual);
        assertEquals(expected.hashCode(), actual.hashCode());
    }

    @Test
    void testPriceShouldHaveMorePriorityThanASecondOne() {
        // Given
        BrandId brandId = BrandIdMother.random();
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);
        PriceList priceList = PriceListMother.random();
        ProductId productId = ProductIdMother.random();
        Priority priority1 = new Priority(1);
        Priority priority2 = new Priority(2);
        Money price = MoneyMother.random();
        Currency currency = CurrencyMother.random();

        PriceModel priceModel1 =
                PriceModelMother.from(
                        brandId, startDate, endDate, priceList, productId, priority1, price, currency);

        PriceModel priceModel2 =
                PriceModelMother.from(
                        brandId, startDate, endDate, priceList, productId, priority2, price, currency);

        Integer expected = 1;

        // When
        Integer actual = priceModel2.comparePriority(priceModel1);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void testPriceShouldHaveLessPriorityThanASecondOne() {
        // Given
        BrandId brandId = BrandIdMother.random();
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);
        PriceList priceList = PriceListMother.random();
        ProductId productId = ProductIdMother.random();
        Priority priority1 = new Priority(1);
        Priority priority2 = new Priority(2);
        Money price = MoneyMother.random();
        Currency currency = CurrencyMother.random();

        PriceModel priceModel1 =
                PriceModelMother.from(
                        brandId, startDate, endDate, priceList, productId, priority1, price, currency);

        PriceModel priceModel2 =
                PriceModelMother.from(
                        brandId, startDate, endDate, priceList, productId, priority2, price, currency);

        Integer expected = -1;

        // When
        Integer actual = priceModel1.comparePriority(priceModel2);

        // Then
        assertEquals(expected, actual);
    }
}
