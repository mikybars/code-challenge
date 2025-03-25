package com.github.mikybars.challenge.prices.domain;

public record Priority(int priority) implements Comparable<Priority> {

  @Override
  public int compareTo(Priority other) {
    return Integer.compare(priority, other.priority);
  }
}
