package com.example.lets_play.exception;

public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException(String msg) {
        super(msg);
    }
}
