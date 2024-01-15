package com.test.price.domain.exception;

import com.test.shared.domain.exceptions.DomainException;

public final class PriceDomainException extends DomainException {
  public PriceDomainException(String message) {
    super(message);
  }

  public PriceDomainException(String message, Throwable cause) {
    super(message, cause);
  }
}
