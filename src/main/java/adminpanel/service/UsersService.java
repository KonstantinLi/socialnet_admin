package adminpanel.service;

import adminpanel.api.request.FindPersonRq;
import adminpanel.api.response.PersonRs;
import adminpanel.mappers.PersonMapper;
import adminpanel.model.PersonEntity;
import adminpanel.repositories.PersonsRepository;
import adminpanel.service.search.SearchPersons;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UsersService {

    private final SearchPersons searchPersons;
    private final PersonMapper personMapper;
    private final PersonsRepository personsRepository;

    public String buildUsersPage(Model model, FindPersonRq findPersonRq) {
        model.addAttribute("users", "router-link-active");
        model.addAttribute("usersList", findPersons(findPersonRq));
        return "users";
    }

    public void blockUnblockUser(Long id) {
        PersonEntity person = personsRepository.findById(id).orElseThrow();
        person.setIsBlocked(!person.getIsBlocked());
        personsRepository.save(person);
    }

    private List<PersonRs> findPersons(FindPersonRq personRq) {
        return personsToPersonsRs(searchPersons.findPersons(personRq)).stream()
                .sorted(Comparator.comparing(user -> user.getFirstName() + user.getLastName()))
                .collect(Collectors.toList());
    }

    private List<PersonRs> personsToPersonsRs(List<PersonEntity> posts) {
        return posts.stream().map(personMapper::toPersonRs).collect(Collectors.toList());
    }
}
