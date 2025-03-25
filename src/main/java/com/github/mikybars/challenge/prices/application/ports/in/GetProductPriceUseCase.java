package com.github.mikybars.challenge.prices.application.ports.in;

import com.github.mikybars.challenge.prices.domain.BrandId;
import com.github.mikybars.challenge.prices.domain.ProductId;
import com.github.mikybars.challenge.prices.domain.ProductPrice;
import java.time.LocalDateTime;

public interface GetProductPriceUseCase {

  ProductPrice execute(LocalDateTime applicationDate, ProductId productId, BrandId brandId);
}
