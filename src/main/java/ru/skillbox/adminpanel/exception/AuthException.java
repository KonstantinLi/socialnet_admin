package ru.skillbox.adminpanel.exception;

public class AuthException extends BadRequestException {
    public AuthException(String message) {
        super(message);
    }
}
