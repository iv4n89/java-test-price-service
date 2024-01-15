package com.test.price.application.adapters.input.service;

import com.test.price.application.PriceApplicationTestConfig;
import com.test.price.application.exception.PriceNotFoundException;
import com.test.price.domain.PriceModelMother;
import com.test.price.domain.model.PriceModel;
import com.test.price.domain.ports.input.service.PriceApplicationService;
import com.test.price.domain.ports.input.usecases.PriceFinder;
import com.test.price.domain.ports.output.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(classes = PriceApplicationTestConfig.class)
class PriceApplicationServiceTest {
    @Autowired
    @Qualifier("priceApplicationServiceTest")
    private PriceApplicationService priceApplicationService;

    @Autowired
    @Qualifier("priceFinderTest")
    private PriceFinder priceFinder;

    @Autowired
    @Qualifier("priceRepositoryTest")
    private PriceRepository priceRepository;

    @Test
    void testFindAPrice() {
        // Given
        PriceModel expected = PriceModelMother.random();
        when(priceRepository.findPriceByBrandIdAndProductIdAndApplicationDate(any(), any(), any())).thenReturn(expected);

        // When
        PriceModel actual = priceApplicationService.findPrice(1L, 1L, LocalDateTime.now());

        // Then
        verify(priceRepository, times(1)).findPriceByBrandIdAndProductIdAndApplicationDate(any(), any(), any());
        verifyNoMoreInteractions(priceRepository);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testFindAPriceAndThrowExceptionWhenNotFound() {
        // Given
        LocalDateTime date = LocalDateTime.now();
        when(priceRepository.findPriceByBrandIdAndProductIdAndApplicationDate(any(), any(), any())).thenThrow(new PriceNotFoundException("Price not found"));

        // When
        PriceNotFoundException priceNotFoundException = assertThrows(PriceNotFoundException.class, () -> priceApplicationService.findPrice(1L, 1L, date));

        // Then
        verify(priceRepository, times(1)).findPriceByBrandIdAndProductIdAndApplicationDate(any(), any(), any());
        verifyNoMoreInteractions(priceRepository);

        assertNotNull(priceNotFoundException);
        assertEquals("Price not found", priceNotFoundException.getMessage());
    }
}
