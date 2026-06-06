package com.sara.online_examination_system.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice //tell spring any exception occurs come here and i will solve
//this class handle all errors
public class GlobalExceptionHandler {

    //if exception of this type happen apply the below method
    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<ApiError> handleEmailExists(EmailAlreadyExistException ex)
    {
        ApiError apiError=new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneral(Exception ex) {
        ApiError error = new ApiError(
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException ex)
    {
        ApiError error=new ApiError(ex.getMessage(),HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
