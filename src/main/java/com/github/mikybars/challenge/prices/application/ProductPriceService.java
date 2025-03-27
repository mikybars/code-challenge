package com.github.mikybars.challenge.prices.application;

import com.github.mikybars.challenge.common.NotFoundException;
import com.github.mikybars.challenge.prices.application.ports.in.GetProductPriceUseCase;
import com.github.mikybars.challenge.prices.application.ports.out.ProductPriceRepository;
import com.github.mikybars.challenge.prices.domain.BrandId;
import com.github.mikybars.challenge.prices.domain.ProductId;
import com.github.mikybars.challenge.prices.domain.ProductPrice;
import java.time.LocalDateTime;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ProductPriceService implements GetProductPriceUseCase {

  private final ProductPriceRepository productPriceRepository;

  @Override
  public ProductPrice execute(LocalDateTime applicationDate, ProductId productId, BrandId brandId) {
    return productPriceRepository
        .findTheHighestRankedBy(applicationDate, productId, brandId)
        .orElseThrow(notFoundException(applicationDate, productId, brandId));
  }

  private static Supplier<NotFoundException> notFoundException(
      LocalDateTime applicationDate, ProductId productId, BrandId brandId) {
    return () -> new NotFoundException(
        "no product price found for [applicationDate=%s, productId=%s, brandId=%s]".formatted(
            applicationDate, productId.id(), brandId.id()));
  }
}
