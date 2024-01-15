package com.test.shared.domain.valueobject;

import com.github.javafaker.Faker;

public final class MotherCreator {
  private static final Faker faker = new Faker();

  public static Faker random() {
    return faker;
  }
}
