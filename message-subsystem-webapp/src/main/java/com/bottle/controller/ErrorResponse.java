package com.bottle.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorResponse {
    public static ResponseEntity<String> getErrorResponse(String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", "text/plain");
        return new ResponseEntity<>(message, headers, HttpStatus.BAD_REQUEST);
    }
}
