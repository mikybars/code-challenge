package com.github.mikybars.challenge.prices.application.ports.out;

import com.github.mikybars.challenge.prices.domain.BrandId;
import com.github.mikybars.challenge.prices.domain.ProductId;
import com.github.mikybars.challenge.prices.domain.ProductPrice;
import java.time.LocalDateTime;
import java.util.Optional;

public interface ProductPriceRepository {

  Optional<ProductPrice> findTheHighestRankedBy(LocalDateTime applicationDate, ProductId productId, BrandId brandId);
}
