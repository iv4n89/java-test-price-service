package com.test.price.infrastructure.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.test.price.domain.CurrencyMother;
import com.test.price.domain.PriceListMother;
import com.test.price.domain.PriceModelMother;
import com.test.price.domain.PriorityMother;
import com.test.price.domain.model.PriceModel;
import com.test.price.infrastructure.PriceInfrastructureTestConfig;
import com.test.price.infrastructure.entity.PriceEntity;
import com.test.shared.domain.valueobject.BrandIdMother;
import com.test.shared.domain.valueobject.MoneyMother;
import com.test.shared.domain.valueobject.ProductIdMother;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@SpringBootTest(classes = PriceInfrastructureTestConfig.class)
class PriceDataMapperTest {
    @Test
    void testMapperShouldMapToDomainModel() {
        // Given
        PriceEntity priceEntity = PriceEntity.builder()
                .brandId(BrandIdMother.random().getValue())
                .productId(ProductIdMother.random().getValue())
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .price(MoneyMother.random().getValue())
                .priceList(PriceListMother.random().getValue())
                .curr(CurrencyMother.random().getValue())
                .priority(PriorityMother.random().getValue())
                .build();

        // When
        PriceModel priceModel = PriceDataMapper.toDomainModel(priceEntity);

        // Then
        assertNotNull(priceModel);
        assertEquals(priceEntity.getBrandId(), priceModel.getBrandId().getValue());
        assertEquals(priceEntity.getProductId(), priceModel.getProductId().getValue());
        assertEquals(priceEntity.getStartDate(), priceModel.getStartDate());
        assertEquals(priceEntity.getEndDate(), priceModel.getEndDate());
        assertEquals(priceEntity.getPrice(), priceModel.getPrice().getValue());
        assertEquals(priceEntity.getPriceList(), priceModel.getPriceList().getValue());
        assertEquals(priceEntity.getCurr(), priceModel.getCurrency().getValue());
        assertEquals(priceEntity.getPriority(), priceModel.getPriority().getValue());
    }

    @Test
    void testMapperShouldMapToEntity() {
        // Given
        PriceModel priceModel = PriceModelMother.random();

        // When
        PriceEntity priceEntity = PriceDataMapper.toEntity(priceModel);

        // Then
        assertNotNull(priceEntity);
        assertEquals(priceModel.getBrandId().getValue(), priceEntity.getBrandId());
        assertEquals(priceModel.getProductId().getValue(), priceEntity.getProductId());
        assertEquals(priceModel.getStartDate(), priceEntity.getStartDate());
        assertEquals(priceModel.getEndDate(), priceEntity.getEndDate());
        assertEquals(priceModel.getPrice().getValue(), priceEntity.getPrice());
        assertEquals(priceModel.getPriceList().getValue(), priceEntity.getPriceList());
        assertEquals(priceModel.getCurrency().getValue(), priceEntity.getCurr());
        assertEquals(priceModel.getPriority().getValue(), priceEntity.getPriority());
    }
}
