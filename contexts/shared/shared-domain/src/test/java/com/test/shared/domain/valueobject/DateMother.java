package com.test.shared.domain.valueobject;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public final class DateMother {
  public static Date random() {
    return MotherCreator.random().date().birthday();
  }

  public static LocalDateTime randomLocalDateTime() {
    return MotherCreator.random()
        .date()
        .birthday()
        .toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();
  }
}
