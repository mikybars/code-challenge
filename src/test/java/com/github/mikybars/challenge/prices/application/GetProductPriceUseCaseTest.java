package com.github.mikybars.challenge.prices.application;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import com.github.mikybars.challenge.common.NotFoundException;
import com.github.mikybars.challenge.prices.ProductPrices;
import com.github.mikybars.challenge.prices.application.ports.in.GetProductPriceUseCase;
import com.github.mikybars.challenge.prices.application.ports.out.PriceRepository;
import com.github.mikybars.challenge.prices.domain.BrandId;
import com.github.mikybars.challenge.prices.domain.ProductId;
import com.github.mikybars.challenge.prices.domain.ProductPrice;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetProductPriceUseCaseTest {

  GetProductPriceUseCase getProductPriceUseCase;

  @Mock
  PriceRepository priceRepository;

  @BeforeEach
  void setUp() {
    getProductPriceUseCase = new ProductPriceService(priceRepository);
  }

  @Test
  void returnTheOnlyResult() {
    ProductPrice theOnlyResult = ProductPrices.any();
    when(priceRepository.findAll(
        sometime(), someProduct(), someBrand())
    ).thenReturn(List.of(theOnlyResult));

    ProductPrice productPrice = getProductPriceUseCase.execute(sometime(), someProduct(),
        someBrand());

    assertThat(productPrice).isEqualTo(theOnlyResult);
  }

  @Test
  void returnTheHighestRanked() {
    ProductPrice theLowestRanked = ProductPrices.withPriority(1);
    ProductPrice theHighestRanked = ProductPrices.withPriority(2);
    when(priceRepository.findAll(
        sometime(), someProduct(), someBrand())
    ).thenReturn(List.of(theLowestRanked, theHighestRanked));

    ProductPrice productPrice = getProductPriceUseCase.execute(sometime(), someProduct(),
        someBrand());

    assertThat(productPrice).isEqualTo(theHighestRanked);
  }

  @Test
  void throwWhenNoResults() {
    when(priceRepository.findAll(
        sometime(), someProduct(), someBrand())
    ).thenReturn(emptyList());

    assertThatThrownBy(
        () -> getProductPriceUseCase.execute(sometime(), someProduct(), someBrand())
    )
        .isInstanceOf(NotFoundException.class)
        .hasMessageContainingAll(sometime().toString(), someProduct().id(), someBrand().id());
  }

  static LocalDateTime sometime() {
    return LocalDateTime.parse("2020-06-14T00:00:00");
  }

  static BrandId someBrand() {
    return new BrandId("1");
  }

  static ProductId someProduct() {
    return new ProductId("35456");
  }
}