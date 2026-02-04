package com.example.lets_play.exception;

public class UsernameAlreadyExistsException extends RuntimeException {
    
    public UsernameAlreadyExistsException(String msg) {
        super(msg);
    }
}
