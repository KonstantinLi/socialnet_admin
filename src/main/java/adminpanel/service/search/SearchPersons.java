package adminpanel.service.search;

import adminpanel.api.request.FindPersonRq;
import adminpanel.model.PersonEntity;
import adminpanel.repositories.PersonsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

import static adminpanel.service.search.PersonSpecification.*;

@Component
@RequiredArgsConstructor
public class SearchPersons {

    private final PersonsRepository personsRepository;

    public List<PersonEntity> findPersons(FindPersonRq personRq) {
        PersonEntity person = personsRepository.findByEmail(
                SecurityContextHolder.getContext().getAuthentication().getName()
        ).get();
        Specification<PersonEntity> specification = getAll().and(excludeMe(person.getId()));

        if (personRq.getFirst_name() != null && !personRq.getFirst_name().isBlank()) {
            specification = specification.and(nameContains(personRq.getFirst_name()));
        }
        if (personRq.getLast_name() != null && !personRq.getLast_name().isBlank()) {
            specification = specification.and(lastNameContains(personRq.getLast_name()));
        }
        if (personRq.getAge_from() != null) {
            specification = specification.and(ageFrom(personRq.getAge_from()));
        }
        if (personRq.getAge_to() != null) {
            specification = specification.and(ageTo(personRq.getAge_to()));
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
        return personsRepository.findAll(specification);
    }
}
