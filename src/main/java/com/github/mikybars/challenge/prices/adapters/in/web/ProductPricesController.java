package com.github.mikybars.challenge.prices.adapters.in.web;

import com.github.mikybars.challenge.prices.application.ports.in.FindProductPriceUseCase;
import com.github.mikybars.challenge.prices.domain.BrandId;
import com.github.mikybars.challenge.prices.domain.ProductId;
import java.time.LocalDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ProductPricesController {

  private final FindProductPriceUseCase findProductPriceUseCase;
  private final ProductPriceRestMapper productPriceRestMapper;

  ProductPricesController(FindProductPriceUseCase findProductPriceUseCase,
      ProductPriceRestMapper productPriceRestMapper) {
    this.findProductPriceUseCase = findProductPriceUseCase;
    this.productPriceRestMapper = productPriceRestMapper;
  }

  @GetMapping("/prices")
  ResponseEntity<ProductPriceDto> findPrice(LocalDateTime applicationDate, String productId, String brandId) {
    var productPrice = findProductPriceUseCase.execute(
        applicationDate, new ProductId(productId), new BrandId(brandId));
    var responseDto = productPriceRestMapper.toResponseDto(productPrice);
    return ResponseEntity.ok(responseDto);
  }
}
