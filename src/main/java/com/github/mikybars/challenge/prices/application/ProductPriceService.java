package com.github.mikybars.challenge.prices.application;

import static java.util.Comparator.comparing;

import com.github.mikybars.challenge.common.NotFoundException;
import com.github.mikybars.challenge.prices.application.ports.in.GetProductPriceUseCase;
import com.github.mikybars.challenge.prices.application.ports.out.PriceRepository;
import com.github.mikybars.challenge.prices.domain.BrandId;
import com.github.mikybars.challenge.prices.domain.ProductId;
import com.github.mikybars.challenge.prices.domain.ProductPrice;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import org.springframework.stereotype.Service;

@Service
class ProductPriceService implements GetProductPriceUseCase {

  private final PriceRepository priceRepository;

  ProductPriceService(PriceRepository priceRepository) {
    this.priceRepository = priceRepository;
  }

  @Override
  public ProductPrice execute(LocalDateTime applicationDate, ProductId productId, BrandId brandId) {
    return highestRankedFrom(applicableProductPrices(applicationDate, productId, brandId))
        .orElseThrow(notFoundException(applicationDate, productId, brandId));
  }

  private List<ProductPrice> applicableProductPrices(LocalDateTime applicationDate,
      ProductId productId, BrandId brandId) {
    return priceRepository.findAll(applicationDate, productId, brandId);
  }

  private static Optional<ProductPrice> highestRankedFrom(List<ProductPrice> productPrices) {
    return productPrices.stream()
        .max(comparing(ProductPrice::priority));
  }

  private static Supplier<NotFoundException> notFoundException(
      LocalDateTime applicationDate, ProductId productId, BrandId brandId) {
    return () -> new NotFoundException(
        "no product price found for [applicationDate=%s, productId=%s, brandId=%s]".formatted(
            applicationDate, productId.id(), brandId.id()));
  }
}
