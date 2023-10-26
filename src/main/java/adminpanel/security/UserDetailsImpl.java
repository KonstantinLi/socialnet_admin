package adminpanel.security;

import adminpanel.model.PersonEntity;
import adminpanel.model.RoleEntity;
import adminpanel.repositories.RolesRepository;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static adminpanel.model.enums.Roles.ADMIN;
import static adminpanel.model.enums.Roles.MODER;

@Data
@Builder
public class UserDetailsImpl implements UserDetails {

    private final PersonEntity personEntity;

    private final RolesRepository rolesRepository;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Optional<RoleEntity> role = rolesRepository.findByPerson(personEntity);
        if (role.isPresent()) {
            if (role.get().getRole().equals(ADMIN)) {
                return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else if (role.get().getRole().equals(MODER)) {
                return List.of(new SimpleGrantedAuthority("ROLE_MODER"));
            }
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return personEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return personEntity.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
