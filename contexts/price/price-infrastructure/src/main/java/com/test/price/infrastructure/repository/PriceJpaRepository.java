package com.test.price.infrastructure.repository;

import com.test.price.infrastructure.entity.PriceEntity;
import com.test.price.infrastructure.entity.PriceEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, PriceEntityId> {
  @Query(
      "SELECT p FROM PriceEntity p WHERE p.brandId = ?1 AND p.productId = ?2 AND p.startDate <= ?3 AND p.endDate >= ?3 ORDER BY p.priority DESC limit 1")
  Optional<PriceEntity> findByBrandIdAndProductIdAndStartDate(
      Long brandId, Long productId, LocalDateTime date);
}
