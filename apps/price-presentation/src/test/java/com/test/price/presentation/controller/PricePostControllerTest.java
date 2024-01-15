package com.test.price.presentation.controller;

import com.test.price.application.dto.PriceFinderRequestDto;
import com.test.price.presentation.PricePresentationTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@Sql({"/init-schema.sql"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@SpringBootTest(classes = PricePresentationTestConfig.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PricePostControllerTest {
    @Autowired
    private WebTestClient client;

    @Test
    void testRequestOn14thJune10AM() {
        // Given
        PriceFinderRequestDto request = new PriceFinderRequestDto(1L, 35455L, LocalDateTime.of(2020, 6, 14, 10, 0,0));

        // When
        client.post()
                .uri("/price/find")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                // Then
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.priceList").isEqualTo(1)
                .jsonPath("$.brandId").isEqualTo(1)
                .jsonPath("$.productId").isEqualTo(35455)
                .jsonPath("$.price").isEqualTo("35.50 EUR")
                .jsonPath("$.startDate").isEqualTo("2020-06-14T00:00:00")
                .jsonPath("$.endDate").isEqualTo("2020-12-31T23:59:59");
    }

    @Test
    void testRequestOn14thJune16PM() {
        // Given
        PriceFinderRequestDto request = new PriceFinderRequestDto(1L, 35455L, LocalDateTime.of(2020, 6, 14, 16, 0,0));

        // When
        client.post()
                .uri("/price/find")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                // Then
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.priceList").isEqualTo(2)
                .jsonPath("$.brandId").isEqualTo(1)
                .jsonPath("$.productId").isEqualTo(35455)
                .jsonPath("$.price").isEqualTo("25.45 EUR")
                .jsonPath("$.startDate").isEqualTo("2020-06-14T15:00:00")
                .jsonPath("$.endDate").isEqualTo("2020-06-14T18:30:00");
    }

    @Test
    void testRequestOn14thJune21PM() {
        // Given
        PriceFinderRequestDto request = new PriceFinderRequestDto(1L, 35455L, LocalDateTime.of(2020, 6, 14, 21, 0,0));

        // When
        client.post()
                .uri("/price/find")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                // Then
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.priceList").isEqualTo(1)
                .jsonPath("$.brandId").isEqualTo(1)
                .jsonPath("$.productId").isEqualTo(35455)
                .jsonPath("$.price").isEqualTo("35.50 EUR")
                .jsonPath("$.startDate").isEqualTo("2020-06-14T00:00:00")
                .jsonPath("$.endDate").isEqualTo("2020-12-31T23:59:59");
    }

    @Test
    void testRequestOn15thJune10AM() {
        // Given
        PriceFinderRequestDto request = new PriceFinderRequestDto(1L, 35455L, LocalDateTime.of(2020, 6, 15, 10, 0,0));

        // When
        client.post()
                .uri("/price/find")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                // Then
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.priceList").isEqualTo(3)
                .jsonPath("$.brandId").isEqualTo(1)
                .jsonPath("$.productId").isEqualTo(35455)
                .jsonPath("$.price").isEqualTo("30.50 EUR")
                .jsonPath("$.startDate").isEqualTo("2020-06-15T00:00:00")
                .jsonPath("$.endDate").isEqualTo("2020-06-15T11:00:00");
    }

    @Test
    void testRequestOn16thJune21PM() {
        // Given
        PriceFinderRequestDto request = new PriceFinderRequestDto(1L, 35455L, LocalDateTime.of(2020, 6, 16, 21, 0,0));

        // When
        client.post()
                .uri("/price/find")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                // Then
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.priceList").isEqualTo(4)
                .jsonPath("$.brandId").isEqualTo(1)
                .jsonPath("$.productId").isEqualTo(35455)
                .jsonPath("$.price").isEqualTo("38.95 EUR")
                .jsonPath("$.startDate").isEqualTo("2020-06-15T16:00:00")
                .jsonPath("$.endDate").isEqualTo("2020-12-31T23:59:59");
    }

    @Test
    void testRequestNotFound() {
        // Given
        PriceFinderRequestDto request = new PriceFinderRequestDto(1L, 35455L, LocalDateTime.of(2021, 6, 16, 21, 0,0));

        // When
        client.post()
                .uri("/price/find")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                // Then
                .expectStatus().isNotFound();
    }
}
