package com.test.application.dto;

import com.test.price.domain.model.PriceModel;
import com.test.shared.application.Response;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PriceFinderResponseDto implements Response {
    private final Long productId;
    private final Long brandId;
    private final Long priceList;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final String price;

    /**
     * Creates a PriceFinderResponseDto object from a PriceModel object.
     *
     * @param price the PriceModel object from which to create the PriceFinderResponseDto object
     * @return the PriceFinderResponseDto object created from the PriceModel object
     */
    public static PriceFinderResponseDto fromDomainModel(PriceModel price) {
        return PriceFinderResponseDto.builder()
                .productId(price.getProductId().getValue())
                .brandId(price.getBrandId().getValue())
                .priceList(price.getPriceList().getValue())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .price(
                        price
                                .getPrice()
                                .getValue()
                                .toPlainString()
                                .concat(" ")
                                .concat(price.getCurrency().getValue()))
                .build();
    }
}
