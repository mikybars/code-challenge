package com.github.mikybars.challenge.prices;

import org.approvaltests.JsonApprovals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

@WebMvcTest
class FindProductPriceTest {

  @Autowired
  WebTestClient webClient;

  @Test
  void findProductPrice() {
    ResponseSpec response = webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/prices")
            .queryParam("applicationDate", sometime())
            .queryParam("productId", productFound())
            .queryParam("brandId", someBrand())
            .build())
        .accept(MediaType.APPLICATION_JSON)
        .exchange();

    response
        .expectStatus().isOk()
        .expectBody(String.class)
        .consumeWith( result ->
            JsonApprovals.verifyJson(result.getResponseBody()));
  }


  @Test
  void doesNotFindProductPrice() {
    ResponseSpec response = webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/prices")
            .queryParam("applicationDate", sometime())
            .queryParam("productId", productNotFound())
            .queryParam("brandId", someBrand())
            .build())
        .accept(MediaType.APPLICATION_JSON)
        .exchange();

    // TODO: check for a problem details object in the response body
    response.expectStatus().isNotFound();
  }

  static String sometime() {
    return "2020-06-14-00.00.00";
  }

  static String someBrand() {
    return "1";
  }

  static String productFound() {
    return "35455";
  }

  static String productNotFound() {
    return "35456";
  }
}