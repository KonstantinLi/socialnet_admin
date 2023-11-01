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
        Specification<PersonEntity> specification = PersonSearchSpecification.getAll().and(PersonSearchSpecification.excludeMe(person.getId()));

        if (personRq.getFirst_name() != null && !personRq.getFirst_name().isBlank()) {
            specification = specification.and(PersonSearchSpecification.nameContains(personRq.getFirst_name()));
        }
        if (personRq.getLast_name() != null && !personRq.getLast_name().isBlank()) {
            specification = specification.and(PersonSearchSpecification.lastNameContains(personRq.getLast_name()));
        }
        if (personRq.getAge_from() != null) {
            specification = specification.and(PersonSearchSpecification.ageFrom(personRq.getAge_from()));
        }
        if (personRq.getAge_to() != null) {
            specification = specification.and(PersonSearchSpecification.ageTo(personRq.getAge_to()));
        }
        if (personRq.getCountry() != null && !personRq.getCountry().isBlank()) {
            specification = specification.and(PersonSearchSpecification.personCountry(personRq.getCountry()));
        }
        if (personRq.getCity() != null && !personRq.getCity().isBlank()) {
            specification = specification.and(PersonSearchSpecification.personCity(personRq.getCity()));
        }
        if (personRq.getStatus() != null && !personRq.getStatus().equals("all")) {
            specification = specification.and(PersonSearchSpecification.blockOrUnblock(personRq.getStatus()));
        }
        return OLDPersonsRepository.findAll(specification);}*/

}
