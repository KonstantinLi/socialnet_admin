package ru.skillbox.adminpanel.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import ru.skillbox.adminpanel.security.JwtTokenUtils;

@Controller
@RequiredArgsConstructor
public class BaseRedirectionController {

    private final JwtTokenUtils jwtTokenUtils;

    @GetMapping("/*")
    public String rootRedirection(@CookieValue(name = "jwtToken", required = false) String token,
                                  HttpServletResponse response) {
        if (token == null || token.isEmpty()) {
            SecurityContextHolder.clearContext();
            return "redirect:admin-console/login";
        }

        if (jwtTokenUtils.validateAccessToken(token)) {
            return "redirect:admin-console/statistics";
        } else {
            SecurityContextHolder.clearContext();

            Cookie tokenCookie = new Cookie("jwtToken", null);
            tokenCookie.setPath("/");

            response.addCookie(tokenCookie);
        }

        return "redirect:admin-console/login";
    }
}
