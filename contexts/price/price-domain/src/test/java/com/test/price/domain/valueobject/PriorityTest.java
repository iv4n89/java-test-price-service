package com.test.price.domain.valueobject;

import com.test.price.domain.PriceDomainTestConfig;
import com.test.price.domain.PriorityMother;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PriceDomainTestConfig.class)
class PriorityTest {
    @Test
    void testPriorityShouldNotBeNull() {
        // Given
        Priority priority = PriorityMother.random();

        // Then
        assertNotNull(priority);
    }

    @Test
    void testPriorityShouldThrowExceptionWhenValueIsNull() {
        // Given
        Integer value = null;

        // When
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Priority(value));

        // Then
        assertEquals("Priority value cannot be null", exception.getMessage());
    }

    @Test
    void testPriorityShouldThrowExceptionWhenValueIsNegative() {
        // Given
        Integer value = -1;

        // When
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Priority(value));

        // Then
        assertEquals("Priority value cannot be negative", exception.getMessage());
    }
}
