package ru.skillbox.adminpanel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseRedirectionController {

    @GetMapping("/")
    public String redirect(@CookieValue(name = "jwtToken", required = false) String token) {
        if (token != null && !token.isEmpty()) {
            return "redirect:admin-console/statistics";
        }

        return "redirect:admin-console/login";
    }
}
