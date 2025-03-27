package com.github.mikybars.challenge.prices.adapters.in.rest;

import com.github.mikybars.challenge.prices.application.ports.in.GetProductPriceUseCase;
import com.github.mikybars.challenge.prices.domain.BrandId;
import com.github.mikybars.challenge.prices.domain.ProductId;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class ProductPricesController {

  private final GetProductPriceUseCase getProductPriceUseCase;
  private final ProductPriceRestMapper productPriceRestMapper;

  @GetMapping("/prices")
  ResponseEntity<ProductPriceDto> findPrice(LocalDateTime applicationDate, String productId, String brandId) {
    var productPrice = getProductPriceUseCase.execute(
        applicationDate, new ProductId(productId), new BrandId(brandId));
    var responseDto = productPriceRestMapper.toResponseDto(productPrice);
    return ResponseEntity.ok(responseDto);
  }
}
