package ru.skillbox.adminpanel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.skillbox.adminpanel.dto.request.FindPersonRq;
import ru.skillbox.adminpanel.dto.response.PersonRs;
import ru.skillbox.adminpanel.entity.Person;
import ru.skillbox.adminpanel.mapper.PersonMapper;
import ru.skillbox.adminpanel.repository.PersonRepository;
import ru.skillbox.adminpanel.util.PersonSearchExecutor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    /*private final SearchPersons searchPersons;
    private final PersonMapper personMapper;
    private final PersonsRepository personsRepository;*/
    private final PersonSearchExecutor personSearchExecutor;

    private final PersonMapper personMapper;
    private final PersonRepository personRepository;

    public String buildUsersPage(Model model, FindPersonRq findPersonRq) {
        model.addAttribute("users", "router-link-active");
        model.addAttribute("usersList", findPersons(findPersonRq));
        return "users";
    }

    private List<PersonRs> findPersons(FindPersonRq personRq) {
        List<PersonRs> personRsList = personsToPersonsRs(personSearchExecutor.findPersons(personRq)).stream()
                .sorted(Comparator.comparing(user -> user.getFirstName() + user.getLastName()))
                .collect(Collectors.toList());
        return personRsList;
    }

    /*public void blockUnblockUser(Long id) {
        PersonEntity person = personsRepository.findById(id).orElseThrow();
        person.setIsBlocked(!person.getIsBlocked());
        personsRepository.save(person);
    }

    private List<PersonRs> findPersons(FindPersonRq personRq) {
        return personsToPersonsRs(searchPersons.findPersons(personRq)).stream()
                .sorted(Comparator.comparing(user -> user.getFirstName() + user.getLastName()))
                .collect(Collectors.toList());
    }
    */

    private List<PersonRs> personsToPersonsRs(List<Person> persons) {
        return persons.stream().map(personMapper::personToPersonRs).collect(Collectors.toList());
    }

    public void blockUnblockUser(Long id) {
        personRepository.findById(id).ifPresent(person -> {
            person.setIsBlocked(!person.getIsBlocked());
            personRepository.save(person);
        });
    }
}
