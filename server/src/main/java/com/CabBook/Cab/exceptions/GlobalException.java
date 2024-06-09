package com.CabBook.Cab.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {

  @ExceptionHandler(UserException.class)
  public ResponseEntity<ErrorDetails> userExceptionHandler(UserException ue, WebRequest req) {
    ErrorDetails err = new ErrorDetails(ue.getMessage(), req.getDescription(false), LocalDateTime.now());
    return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
  }

}
