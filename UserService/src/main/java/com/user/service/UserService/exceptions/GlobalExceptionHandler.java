package com.user.service.UserService.exceptions;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.user.service.UserService.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
        String m = ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(m).success(true).httpStatus(HttpStatus.NOT_FOUND).build();
            return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);

    }
}
