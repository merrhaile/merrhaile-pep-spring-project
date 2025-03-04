package com.example.exception;

import javax.security.sasl.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(UserDuplicateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleDuplicateUsername(UserDuplicateException exception){
        return exception.getMessage();
    }

    @ExceptionHandler(UserCredentialException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleRegistirationException(UserCredentialException exception){
        return exception.getMessage();
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleLoginException(AuthenticationException exception){
        return exception.getMessage();
    }
}
