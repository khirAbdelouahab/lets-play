package com.example.lets_play.exception;

public class AuthenticationException extends RuntimeException{

    public AuthenticationException(String msg) {
        super(msg);
    }
}
