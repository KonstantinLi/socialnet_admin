package ru.skillbox.adminpanel.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BadRequestException.class})
    public String handleCommonExceptions(BadRequestException exception) {

        return "redirect:/";
    }
}