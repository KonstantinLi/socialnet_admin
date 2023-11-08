package ru.skillbox.adminpanel.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skillbox.adminpanel.entity.Admin;
import ru.skillbox.adminpanel.entity.Person;
import ru.skillbox.adminpanel.entity.Role;
import ru.skillbox.adminpanel.repository.AdminRepository;
import ru.skillbox.adminpanel.repository.PersonRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        try {
            Admin admin = adminRepository.findByAdminLoginIgnoreCase(email)
                    .orElseThrow(() ->
                            new UsernameNotFoundException(String.format(
                                    "Пользователь с email %s не найден", email)));

            return new User(admin.getId() + "," + admin.getAdminLogin(),
                    admin.getPassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_" + admin.getRole().name())));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Переданное Id пользователя не является числом");
        }
    }
}
