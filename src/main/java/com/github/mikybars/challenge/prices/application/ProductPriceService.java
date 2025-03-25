package com.github.mikybars.challenge.prices.application;

import com.github.mikybars.challenge.prices.application.ports.in.GetProductPriceUseCase;
import com.github.mikybars.challenge.prices.domain.BrandId;
import com.github.mikybars.challenge.prices.domain.ProductId;
import com.github.mikybars.challenge.prices.domain.ProductPrice;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
class ProductPriceService implements GetProductPriceUseCase {

  @Override
  public ProductPrice execute(LocalDateTime applicationDate, ProductId productId, BrandId brandId) {
    return null;
  }
}
