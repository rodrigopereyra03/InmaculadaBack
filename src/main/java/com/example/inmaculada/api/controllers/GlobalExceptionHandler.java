package com.example.inmaculada.api.controllers;

import com.example.inmaculada.domain.exceptions.ImageNotFoundException;
import com.example.inmaculada.domain.exceptions.ProductNotFoundException;
import com.example.inmaculada.domain.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(EmailAlreadyExistsException.class)
//    public ResponseEntity<String> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

//    @ExceptionHandler(OrderNotFoundException.class)
//    public ResponseEntity<String> handleOrderNotFoundException(OrderNotFoundException ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(ImageNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
