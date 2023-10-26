package adminpanel.security;

import adminpanel.model.PersonEntity;
import adminpanel.repositories.PersonsRepository;
import adminpanel.repositories.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PersonsRepository personsRepository;

    private final RolesRepository rolesRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        PersonEntity person = personsRepository.findByEmail(email).orElseThrow();
        return new UserDetailsImpl(person, rolesRepository);
    }
}
