package com.example.lets_play.exception;

public class EmailAlreadyExistException extends RuntimeException {
    public EmailAlreadyExistException(String msg) {
        super(msg);
    }
}
