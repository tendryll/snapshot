package io.aleksandr.labs.snapshot.exception.handler;

import io.aleksandr.labs.snapshot.exception.BadGatewayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(value = {BadGatewayException.class})
  protected ResponseEntity<Void> handleConflict() {
    return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
  }
}