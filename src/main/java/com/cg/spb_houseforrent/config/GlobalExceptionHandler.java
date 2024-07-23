package com.cg.spb_houseforrent.config;

import com.cg.spb_houseforrent.exception.DateForrentException;
import com.fasterxml.jackson.databind.DatabindException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DateForrentException.class)
    public ResponseEntity<?> handleDateForrentException(DateForrentException ex) {

        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}

