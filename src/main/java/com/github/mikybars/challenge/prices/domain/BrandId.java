package com.github.mikybars.challenge.prices.domain;

public record BrandId(String id) {

  public static BrandId zara() {
    return new BrandId("1");
  }
}
