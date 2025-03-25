package com.github.mikybars.challenge.prices.adapters.in.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.wimdeblauwe.errorhandlingspringbootstarter.ApiErrorResponse;
import io.github.wimdeblauwe.errorhandlingspringbootstarter.ApiErrorResponseCustomizer;
import java.net.URI;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

@Configuration
public class RestConfiguration {

  private enum ErrorType {
    NOT_FOUND(HttpStatus.NOT_FOUND,
        URI.create("https://problems-registry.smartbear.com/not-found"));

    private final HttpStatus httpStatus;
    private final URI uri;

    ErrorType(HttpStatus httpStatus, URI uri) {
      this.httpStatus = httpStatus;
      this.uri = uri;
    }
  }

  @Bean
  ApiErrorResponseCustomizer apiErrorResponseCustomizer() {
    return response ->
        response.addErrorProperties(from(problemDetailBasedOn(response)));
  }

  private static Map<String, Object> from(ProblemDetail problemDetail) {
    return new ObjectMapper().convertValue(problemDetail, new TypeReference<>() {
    });
  }

  private static ProblemDetail problemDetailBasedOn(ApiErrorResponse response) {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
        response.getHttpStatus(), response.getMessage());
    for (ErrorType errorType : ErrorType.values()) {
      if (errorType.httpStatus == response.getHttpStatus()) {
        problemDetail.setType(errorType.uri);
      }
    }
    return problemDetail;
  }
}
