package ru.itmentor.spring.boot_security.demo.ExeptionHandling;

public class NoSuchUserException extends RuntimeException{
    public NoSuchUserException(String message) {
        super(message);
    }

}
