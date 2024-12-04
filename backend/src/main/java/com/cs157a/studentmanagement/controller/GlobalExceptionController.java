package com.cs157a.studentmanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionController {
   @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
   public ResponseEntity<String> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
      return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
              .body("Method not allowed: " + ex.getMethod());
   }

   @ExceptionHandler(Exception.class)
   public ResponseEntity<String> handleException(Exception ex) {
      ex.printStackTrace();  // Log the exception
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("An unexpected error occurred.");
   }
}
