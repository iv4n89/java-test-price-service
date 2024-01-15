package com.test.price.presentation.exception.handler;

import com.test.price.application.exception.PriceNotFoundException;
import com.test.price.domain.exception.PriceDomainException;
import com.test.shared.application.ErrorDto;
import com.test.shared.infrastructure.exception.handler.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class PriceControllerAdvice extends GlobalExceptionHandler {
  @ResponseBody
  @ExceptionHandler(value = {PriceDomainException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorDto handleException(PriceDomainException exception) {
    log.error(exception.getMessage(), exception);
    return ErrorDto.builder()
        .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
        .message(exception.getMessage())
        .build();
  }

  @ResponseBody
  @ExceptionHandler(value = {PriceNotFoundException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorDto handleException(PriceNotFoundException exception) {
    log.error(exception.getMessage(), exception);
    return ErrorDto.builder()
        .code(HttpStatus.NOT_FOUND.getReasonPhrase())
        .message(exception.getMessage())
        .build();
  }
}
