package adminpanel.service;

import adminpanel.api.request.LoginRq;
import adminpanel.errors.PasswordException;
import adminpanel.errors.WrongEmailException;
import adminpanel.model.PersonEntity;
import adminpanel.repositories.PersonsRepository;
import adminpanel.security.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final PersonsRepository personsRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWTUtil jwtUtil;

    public void loginUser(LoginRq loginRq, HttpServletResponse response) throws PasswordException, WrongEmailException {

        Cookie jwtToken = new Cookie("jwtToken", jwtUtil.createToken("shepardcmndr@gmail.com"));
        jwtToken.setPath("/");
        response.addCookie(jwtToken);

        /*Optional<PersonEntity> person = personsRepository.findByEmail(loginRq.getEmail());
        if (person.isPresent()) {
            if (!passwordEncoder.matches(loginRq.getPassword(), person.get().getPassword())) {
                throw new PasswordException("Wrong password for email: '" + loginRq.getEmail() + "'");
            } else {
                Cookie jwtToken = new Cookie("jwtToken", jwtUtil.createToken(person.get().getEmail()));
                jwtToken.setPath("/");
                response.addCookie(jwtToken);
            }
        } else {
            throw new WrongEmailException("User with email: '" + loginRq.getEmail() + "' not found");
        }*/
    }

    public void logout(HttpServletResponse response) {
        SecurityContextHolder.clearContext();
        Cookie emptyJwt = new Cookie("jwtToken", null);
        emptyJwt.setPath("/");
        emptyJwt.setMaxAge(0);
        response.addCookie(emptyJwt);

    }
}
