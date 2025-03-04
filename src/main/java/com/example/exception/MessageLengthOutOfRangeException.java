package com.example.exception;

public class MessageLengthOutOfRangeException extends RuntimeException {
    public MessageLengthOutOfRangeException(String message){
        super(message);
    }
}
