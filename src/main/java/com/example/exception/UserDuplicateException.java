package com.example.exception;

public class UserDuplicateException extends RuntimeException{
    public UserDuplicateException(String message){
        super(message);
    }
}
