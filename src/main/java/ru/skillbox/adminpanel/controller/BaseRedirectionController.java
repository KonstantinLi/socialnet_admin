package ru.skillbox.adminpanel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import ru.skillbox.adminpanel.security.JwtTokenUtils;

@Controller
@RequiredArgsConstructor
public class BaseRedirectionController {

    private final JwtTokenUtils jwtTokenUtils;

    @GetMapping("/")
    public String redirect(@CookieValue(name = "jwtToken", required = false) String token) {
        if (token == null || token.isEmpty()) {
            return "redirect:admin-console/login";
        }

        if (jwtTokenUtils.validateAccessToken(token)) {
            return "redirect:admin-console/statistics";
        }

        return "redirect:admin-console/login";
    }
}
