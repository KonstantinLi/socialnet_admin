package ru.skillbox.adminpanel.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.skillbox.adminpanel.dto.request.FindPersonRq;
import ru.skillbox.adminpanel.entity.Person;
import ru.skillbox.adminpanel.repository.PersonRepository;

import java.util.List;

import static ru.skillbox.adminpanel.util.PersonSearchSpecification.*;

@Component
@RequiredArgsConstructor
public class PersonSearchExecutor {

    private final PersonRepository personRepository;

    public List<Person> findPersons(FindPersonRq personRq) {
        /*Person person = personRepository.findByEmail(
                SecurityContextHolder.getContext().getAuthentication().getName()
        ).get(); */

        Specification<Person> specification = getAll();

        if (personRq.getFirstName() != null && !personRq.getFirstName().isBlank()) {
            specification = specification.and(nameContains(personRq.getFirstName()));
        }
        if (personRq.getLastName() != null && !personRq.getLastName().isBlank()) {
            specification = specification.and(lastNameContains(personRq.getLastName()));
        }
        if (personRq.getAgeFrom() != null) {
            specification = specification.and(ageFrom(personRq.getAgeFrom()));
        }
        if (personRq.getAgeTo() != null) {
            specification = specification.and(ageTo(personRq.getAgeTo()));
        }
        if (personRq.getCountry() != null && !personRq.getCountry().isBlank()) {
            specification = specification.and(personCountry(personRq.getCountry()));
        }
        if (personRq.getCity() != null && !personRq.getCity().isBlank()) {
            specification = specification.and(personCity(personRq.getCity()));
        }
        if (personRq.getStatus() != null && !personRq.getStatus().equals("all")) {
            specification = specification.and(blockOrUnblock(personRq.getStatus()));
        }
        List<Person> all = personRepository.findAll(specification);
        return all;
    }
}
