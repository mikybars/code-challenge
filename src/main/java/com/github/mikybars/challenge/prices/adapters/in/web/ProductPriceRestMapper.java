package com.github.mikybars.challenge.prices.adapters.in.web;

import com.github.mikybars.challenge.prices.domain.ProductPrice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductPriceRestMapper {

  @Mapping(target = "productId", source = "productId.id")
  @Mapping(target = "brandId", source = "brandId.id")
  @Mapping(target = "priceListId", source = "priceListId.id")
  @Mapping(target = "amount", source = "price.amount")
  @Mapping(target = "currencyCode", source = "price.currency")
  ProductPriceDto toResponseDto(ProductPrice productPrice);
}
