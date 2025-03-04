package com.example.exception;

public class UserCredentialException extends RuntimeException {
   
    public UserCredentialException(String message){
        super(message);
    }
}
