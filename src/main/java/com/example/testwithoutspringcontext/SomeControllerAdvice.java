package com.example.testwithoutspringcontext;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SomeControllerAdvice {
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handle(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(e.getMessage()));
    }
}
