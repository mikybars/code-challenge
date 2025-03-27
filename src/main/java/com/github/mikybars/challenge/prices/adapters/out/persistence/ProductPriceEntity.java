package com.github.mikybars.challenge.prices.adapters.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;

@Entity
@Table(name = "product_price")
@Getter
public class ProductPriceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  UUID id;
  String productId;
  String brandId;
  String priceListId;
  LocalDateTime startDate;
  LocalDateTime endDate;
  String amount;
  String currency;
}
