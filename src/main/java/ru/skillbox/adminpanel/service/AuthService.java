package ru.skillbox.adminpanel.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skillbox.adminpanel.dto.request.LoginRq;
import ru.skillbox.adminpanel.entity.Admin;
import ru.skillbox.adminpanel.exception.AuthException;
import ru.skillbox.adminpanel.repository.AdminRepository;
import ru.skillbox.adminpanel.repository.PersonRepository;
import ru.skillbox.adminpanel.security.JwtTokenUtils;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PersonRepository personRepository;
    private final JwtTokenUtils jwtTokenUtils;
    private final AdminRepository adminRepository;

    public void login(LoginRq loginRq, HttpServletResponse response) {
        Admin admin = adminRepository.findByAdminLogin(loginRq.getEmail()).orElseThrow(
                () -> new AuthException("Пользователь не найден"));

        String password = getDecodedPassword(admin.getPassword());

        if (!loginRq.getPassword().equals(password)) {
            throw new AuthException("Неверный пароль");
        }

        Cookie jwtToken = new Cookie("jwtToken", getToken(admin));
        jwtToken.setPath("/");
        response.addCookie(jwtToken);
    }

    public String getDecodedPassword(String password) {
        byte[] decodedBytes = Base64.getDecoder().decode(password);
        return new String(decodedBytes);
    }

    private String getToken(Admin admin) {
        return jwtTokenUtils.generateToken(admin);
    }
}
