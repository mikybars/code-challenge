package com.github.mikybars.challenge.prices.adapters.out.persistence;

import com.github.mikybars.challenge.prices.application.ports.out.ProductPriceRepository;
import com.github.mikybars.challenge.prices.domain.BrandId;
import com.github.mikybars.challenge.prices.domain.ProductId;
import com.github.mikybars.challenge.prices.domain.ProductPrice;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class InMemoryProductPriceRepository implements ProductPriceRepository {

  private final JpaRepository jpaRepository;
  private final ProductPricePersistenceMapper persistenceMapper;

  @Override
  public Optional<ProductPrice> findTheHighestRankedBy(
      LocalDateTime applicationDate, ProductId productId, BrandId brandId) {
    return jpaRepository
        .findTheHighestRankedBy(applicationDate, productId.id(), brandId.id())
        .map(persistenceMapper::toDomain);
  }

  interface JpaRepository extends
      org.springframework.data.jpa.repository.JpaRepository<ProductPriceEntity, UUID> {

    @Query(value = """
        SELECT * FROM product_price
        WHERE product_id = :productId
          AND brand_id = :brandId
          AND start_date <= :applicationDate
          AND end_date >= :applicationDate
        ORDER BY rank DESC
        LIMIT 1
        """,
        nativeQuery = true)
    Optional<ProductPriceEntity> findTheHighestRankedBy(
        LocalDateTime applicationDate, String productId, String brandId);
  }
}
