package com.github.mikybars.challenge.prices;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("standalone")
class GetProductPriceIT {

  @Autowired
  WebTestClient webClient;

  @Test
  @Sql("classpath:prices.sql")
  void getProductPrice() {
    ResponseSpec response = webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/prices")
            .queryParam("applicationDate", LocalDateTime.parse("2023-01-01T00:00:00"))
            .queryParam("productId", "p1")
            .queryParam("brandId", "b1")
            .build())
        .accept(MediaType.APPLICATION_JSON)
        .exchange();

    response
        .expectStatus().isOk()
        .expectBody().jsonPath("$.productId").isNotEmpty();
  }
}