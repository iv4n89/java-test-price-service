package com.test.price.application.dto;

import java.time.LocalDateTime;

public record PriceFinderRequestDto(
        Long brandId,
        Long productId,
        LocalDateTime date
) {}
