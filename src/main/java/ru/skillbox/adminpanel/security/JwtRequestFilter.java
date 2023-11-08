package ru.skillbox.adminpanel.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenUtils jwtTokenUtils;

    @SuppressWarnings("NullableProblems")
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();

        if (cookies == null || cookies.length == 0) {
            filterChain.doFilter(request, response);
            return;
        }

        String authJwt = cookies[0].getValue();

        if (authJwt == null || !jwtTokenUtils.validateAccessToken(authJwt)) {
            filterChain.doFilter(request, response);
            return;
        }

        setAuthenticationContext(authJwt);
        filterChain.doFilter(request, response);
    }

    private void setAuthenticationContext(String token) {
        Authentication authentication = jwtTokenUtils.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
