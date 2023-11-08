package ru.skillbox.adminpanel.exception;

public class MainAppConnectionFailedException extends BadRequestException {

    public MainAppConnectionFailedException(String message) {
        super(message);
    }
}
