package ru.skillbox.adminpanel.exception;

public class PostNotFoundException extends BadRequestException {
    public PostNotFoundException(String message) {
        super(message);
    }
}
