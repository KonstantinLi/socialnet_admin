package ru.skillbox.adminpanel.service.search;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchPersons {
/*
    private final OLDPersonsRepository OLDPersonsRepository;

    public List<PersonEntity> findPersons(FindPersonRq personRq) {
        PersonEntity person = OLDPersonsRepository.findByEmail(
                SecurityContextHolder.getContext().getAuthentication().getName()
        ).get();
        Specification<PersonEntity> specification = PersonSpecification.getAll().and(PersonSpecification.excludeMe(person.getId()));

        if (personRq.getFirst_name() != null && !personRq.getFirst_name().isBlank()) {
            specification = specification.and(PersonSpecification.nameContains(personRq.getFirst_name()));
        }
        if (personRq.getLast_name() != null && !personRq.getLast_name().isBlank()) {
            specification = specification.and(PersonSpecification.lastNameContains(personRq.getLast_name()));
        }
        if (personRq.getAge_from() != null) {
            specification = specification.and(PersonSpecification.ageFrom(personRq.getAge_from()));
        }
        if (personRq.getAge_to() != null) {
            specification = specification.and(PersonSpecification.ageTo(personRq.getAge_to()));
        }
        if (personRq.getCountry() != null && !personRq.getCountry().isBlank()) {
            specification = specification.and(PersonSpecification.personCountry(personRq.getCountry()));
        }
        if (personRq.getCity() != null && !personRq.getCity().isBlank()) {
            specification = specification.and(PersonSpecification.personCity(personRq.getCity()));
        }
        if (personRq.getStatus() != null && !personRq.getStatus().equals("all")) {
            specification = specification.and(PersonSpecification.blockOrUnblock(personRq.getStatus()));
        }
        return OLDPersonsRepository.findAll(specification);}*/

}
