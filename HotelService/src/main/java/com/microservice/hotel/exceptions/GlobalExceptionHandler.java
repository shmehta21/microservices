package com.microservice.hotel.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex){
        String message = ex.getMessage();
        Map<String, Object> excMap = new HashMap<>();
        excMap.put("message", ex.getMessage());
        excMap.put("status", HttpStatus.NOT_FOUND);

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(excMap);
    }
}
