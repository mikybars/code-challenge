package com.github.mikybars.challenge.prices;

import static org.mockito.Mockito.when;

import com.github.mikybars.challenge.common.NotFoundException;
import com.github.mikybars.challenge.prices.adapters.in.web.ProductPriceRestMapperImpl;
import com.github.mikybars.challenge.prices.adapters.in.web.RestConfiguration;
import com.github.mikybars.challenge.prices.application.ports.in.GetProductPriceUseCase;
import com.github.mikybars.challenge.prices.domain.BrandId;
import com.github.mikybars.challenge.prices.domain.Money;
import com.github.mikybars.challenge.prices.domain.PriceListId;
import com.github.mikybars.challenge.prices.domain.Priority;
import com.github.mikybars.challenge.prices.domain.ProductId;
import com.github.mikybars.challenge.prices.domain.ProductPrice;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import org.approvaltests.JsonApprovals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

@WebMvcTest
@Import({
    ProductPriceRestMapperImpl.class,
    RestConfiguration.class
})
class GetProductPriceTest {

  @Autowired
  WebTestClient webClient;

  @MockitoBean
  GetProductPriceUseCase getProductPriceUseCase;

  @Test
  void getProductPrice() {
    when(getProductPriceUseCase.execute(
        sometime(), productPriceFound().productId(), someBrand())
    ).thenReturn(productPriceFound());

    ResponseSpec response = webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/prices")
            .queryParam("applicationDate", sometime().toString())
            .queryParam("productId", productPriceFound().productId().id())
            .queryParam("brandId", someBrand().id())
            .build())
        .accept(MediaType.APPLICATION_JSON)
        .exchange();

    response
        .expectStatus().isOk()
        .expectBody(String.class).consumeWith(result ->
            JsonApprovals.verifyJson(result.getResponseBody()));
  }

  @Test
  void doesNotGetProductPrice() {
    when(getProductPriceUseCase.execute(
        sometime(), productNotFoundId(), someBrand())
    ).thenThrow(new NotFoundException("no price found for input params"));

    ResponseSpec response = webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/prices")
            .queryParam("applicationDate", sometime().toString())
            .queryParam("productId", productNotFoundId().id())
            .queryParam("brandId", someBrand().id())
            .build())
        .accept(MediaType.APPLICATION_JSON)
        .exchange();

    response
        .expectStatus().isNotFound()
        .expectBody(String.class).consumeWith(result ->
            JsonApprovals.verifyJson(result.getResponseBody()));
  }

  static ProductPrice productPriceFound() {
    return new ProductPrice(new ProductId("35455"), BrandId.zara(), new PriceListId("1"),
        LocalDateTime.parse("2020-06-14T00:00:00"),
        LocalDateTime.parse("2020-12-31T23:59:59"),
        new Money(BigDecimal.valueOf(3550, 2), Currency.getInstance("EUR")));
  }

  static LocalDateTime sometime() {
    return LocalDateTime.parse("2020-06-14T00:00:00");
  }

  static BrandId someBrand() {
    return new BrandId("1");
  }

  static ProductId productNotFoundId() {
    return new ProductId("35456");
  }
}