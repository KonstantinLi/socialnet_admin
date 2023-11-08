package ru.skillbox.adminpanel.exception;

public class AdminIdentificationException extends BadRequestException {
    public AdminIdentificationException(String message) {
        super(message);
    }
}
