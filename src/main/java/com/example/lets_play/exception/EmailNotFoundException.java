package com.example.lets_play.exception;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException(String msg) {
        super(msg);
    }
}
