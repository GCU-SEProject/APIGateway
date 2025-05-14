package com.example.apigatewayservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        // 간단하게 500 에러 처리
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Gateway error: " + ex.getMessage());
    }
}