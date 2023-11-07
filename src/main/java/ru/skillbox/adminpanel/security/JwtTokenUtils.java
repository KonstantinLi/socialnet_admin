package ru.skillbox.adminpanel.security;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import ru.skillbox.adminpanel.config.JwtProperties;
import ru.skillbox.adminpanel.entity.Admin;
import ru.skillbox.adminpanel.entity.Role;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtTokenUtils {
    private final JwtProperties jwtProperties;

    public String generateToken(Admin admin) {

        //TODO для админов надо бы другую роль, наверно?
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", List.of(admin.getRole()));

        Date now = new Date();
        Date expired = new Date(now.getTime() + jwtProperties.getLifetime().toMillis());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(admin.getId() + "," + admin.getAdminLogin())
                .setIssuedAt(now)
                .setExpiration(expired)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .compact();
    }

    public Authentication getAuthentication(String token) {
        return new UsernamePasswordAuthenticationToken(
                getSubject(token),
                null,
                getRoles(token)
                        .stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList()
        );
    }

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException | IllegalArgumentException |
                 MalformedJwtException | UnsupportedJwtException |
                 SignatureException ex) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    public String getSubject(String token) {
        return getClaims(token).getSubject();
    }

    public Long getId(String token) {
        return Long.parseLong(getSubject(token).split(",")[0]);
    }

    public String getEmail(String token) {
        return getSubject(token).split(",")[1];
    }

    //TODO Сонар жалуется, но я не понимаю как исправить
    public List<String> getRoles(String token) {

        return getClaims(token).get("roles", List.class);
    }
}
