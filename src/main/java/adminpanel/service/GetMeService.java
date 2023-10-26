package adminpanel.service;

import adminpanel.api.response.MeRs;
import adminpanel.mappers.PersonMapper;
import adminpanel.model.PersonEntity;
import adminpanel.repositories.PersonsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetMeService {

    private final PersonsRepository personsRepository;

    private final PersonMapper personMapper;

    public MeRs getMe() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        PersonEntity person = personsRepository.findByEmail(email).get();
        return personMapper.toMeRs(person);
    }
}
