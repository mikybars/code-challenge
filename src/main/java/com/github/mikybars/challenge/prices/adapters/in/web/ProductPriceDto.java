package com.github.mikybars.challenge.prices.adapters.in.web;

public record ProductPriceDto(String productId, String brandId, String priceListId,
                              String startDate, String endDate,
                              String amount, String currencyCode) {

}
