package adminpanel.errors;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerController {

    @ExceptionHandler({WrongEmailException.class,
            PasswordException.class})
    public String handleLoginExceptions(Exception e) {
        return "redirect:/";
    }
}
