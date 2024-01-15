package com.test.shared.infrastructure.exception.handler;

import com.test.shared.application.ErrorDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ErrorDto.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(exception.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = { IllegalArgumentException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleException(IllegalArgumentException exception) {
        log.error(exception.getMessage(), exception);
        return ErrorDto.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(exception.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = { ValidationException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleException(ValidationException exception) {
        ErrorDto errorDto;
        if (exception instanceof ConstraintViolationException constraintViolationException) {
            final String violations = extractViolationsFromException(constraintViolationException);
            log.error(violations, exception);
            errorDto = ErrorDto.builder()
                    .code(HttpStatus.BAD_GATEWAY.getReasonPhrase())
                    .message(violations)
                    .build();
        } else {
            final String exceptionMessage = exception.getMessage();
            log.error(exceptionMessage, exception);
            errorDto = ErrorDto.builder()
                    .code(HttpStatus.BAD_GATEWAY.getReasonPhrase())
                    .message(exceptionMessage)
                    .build();
        }

        return errorDto;
    }

    private String extractViolationsFromException(ConstraintViolationException exception) {
        return exception.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("--"));
    }

}
