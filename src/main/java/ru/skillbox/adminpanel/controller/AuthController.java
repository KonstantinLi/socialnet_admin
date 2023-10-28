package ru.skillbox.adminpanel.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.skillbox.adminpanel.annotation.Info;
import ru.skillbox.adminpanel.dto.request.LoginRq;
import ru.skillbox.adminpanel.service.AuthService;

@Controller
@RequestMapping("admin-console")
@RequiredArgsConstructor
@Info
public class AuthController {

    private final AuthService authService;

    @ModelAttribute("LoginRq")
    public LoginRq loginRq() {
        return new LoginRq();
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(LoginRq loginRq, HttpServletResponse response) {

        authService.login(loginRq, response);
        return "redirect:statistics";
    }
}
