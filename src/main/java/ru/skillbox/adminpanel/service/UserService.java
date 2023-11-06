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

@Service
@RequiredArgsConstructor
public class UserService {
    private final PersonSearchExecutor personSearchExecutor;
    private final PersonMapper personMapper;
    private final PersonRepository personRepository;

    public String buildUsersPage(Model model, FindPersonRq findPersonRq) {
        model.addAttribute("users", "router-link-active");
        model.addAttribute("usersList", findPersons(findPersonRq));
        return "users";
    }

    private List<PersonRs> findPersons(FindPersonRq personRq) {
        return personsToPersonsRs(personSearchExecutor.findPersons(personRq)).stream()
                .sorted(Comparator.comparing(user -> user.getFirstName() + user.getLastName()))
                .toList();
    }

    private List<PersonRs> personsToPersonsRs(List<Person> persons) {


        List<PersonRs> list = persons.stream().map(personMapper::personToPersonRs).toList();
        return list;
    }

    public void blockUnblockUser(Long id) {
        personRepository.findById(id).ifPresent(person -> {
            person.setIsBlocked(person.getIsBlocked() == null || !person.getIsBlocked());

            personRepository.save(person);
        });
    }
}
