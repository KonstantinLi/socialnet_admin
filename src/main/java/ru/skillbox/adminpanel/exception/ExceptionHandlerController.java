package ru.skillbox.adminpanel.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


//TODO remove unused controller
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerController {

    @ExceptionHandler({WrongEmailException.class,
            PasswordException.class})
    public String handleLoginExceptions(Exception e) {
        return "redirect:/";
    }
}
