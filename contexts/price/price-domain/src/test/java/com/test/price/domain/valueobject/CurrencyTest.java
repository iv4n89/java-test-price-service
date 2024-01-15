package com.test.price.domain.valueobject;

import static org.junit.jupiter.api.Assertions.*;

import com.test.price.domain.CurrencyMother;
import com.test.price.domain.PriceDomainTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PriceDomainTestConfig.class)
class CurrencyTest {
  @Test
  void testCurrencyShouldNotBeEmpty() {
    // Given
    Currency currency = CurrencyMother.random();

    // Then
    assertNotNull(currency);
    assertFalse(currency.getValue().isEmpty());
  }

  @Test
  void testCurrencyShouldThrowExceptionWhenValueIsNull() {
    // Given
    // Currency value will be null

    // When
    IllegalArgumentException illegalArgumentException =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              Currency currency = new Currency(null);
            });

    // Then
    assertEquals("Currency value cannot be null", illegalArgumentException.getMessage());
  }

  @Test
  void testCurrencyShouldThrowExceptionWhenValueIsEmpty() {
    // Given
    // Currency value will be empty

    // When
    IllegalArgumentException illegalArgumentException =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              Currency currency = new Currency("");
            });

    // Then
    assertEquals("Currency value cannot be empty", illegalArgumentException.getMessage());
  }

  @Test
  void testCurrencyShouldThrowExceptionWhenValueIsLessThanThreeCharacters() {
    // Given
    // Currency value will be less than three characters

    // When
    IllegalArgumentException illegalArgumentException =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              Currency currency = new Currency("AB");
            });

    // Then
    assertEquals(
        "Currency value needs to have three characters", illegalArgumentException.getMessage());
  }
}
