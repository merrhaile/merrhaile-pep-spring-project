package com.example.exception;

public class UserLogInException extends RuntimeException{
    public UserLogInException(String message){
        super(message);
    }
}
