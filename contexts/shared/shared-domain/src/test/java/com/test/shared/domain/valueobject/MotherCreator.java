package com.test.shared.domain.valueobject;

import com.github.javafaker.Faker;

/**
 * MotherCreator is a class that helps us to create random values for our tests.
 */
public final class MotherCreator {
  private static final Faker faker = new Faker();

  public static Faker random() {
    return faker;
  }
}
