package com.apod.exception;

public class NoApodException extends RuntimeException {
    public NoApodException(String message) {
        super(message);
    }
}
