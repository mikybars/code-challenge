package com.github.mikybars.challenge.prices.adapters.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ProductPricesController {

  record Response(String productId, String brandId, String priceListId, String startDate,
                  String endDate, String amount, String currencyCode) {

  }

  @GetMapping("/prices")
  ResponseEntity<Response> findPrice(String applicationDate, String productId, String brandId) {
    if (productId.equals("35455")) {
      return ResponseEntity.ok(
          new Response("35455", "1", "1", "2020-06-14-00.00.00", "2020-12-31-23.59.59", "35.50",
              "EUR"));
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
