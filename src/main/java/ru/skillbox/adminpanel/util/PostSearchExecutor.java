package ru.skillbox.adminpanel.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.skillbox.adminpanel.dto.request.FindPostRq;
import ru.skillbox.adminpanel.entity.Person;
import ru.skillbox.adminpanel.entity.Post;
import ru.skillbox.adminpanel.exception.TimeException;
import ru.skillbox.adminpanel.repository.PersonRepository;
import ru.skillbox.adminpanel.repository.PostsRepository;

import java.time.LocalDateTime;
import java.util.List;

import static ru.skillbox.adminpanel.util.PostSearchSpecification.*;

@Component
@RequiredArgsConstructor
public class PostSearchExecutor {

    private final PostsRepository postsRepository;
    private final PersonRepository personRepository;

    public List<Post> findPosts(FindPostRq postRq) throws TimeException {
        Specification<Post> specification = getAll();
        if (postRq.getText() != null && !postRq.getText().isBlank()) {
            specification = specification.and(textLike(postRq.getText()));
        }
        if (postRq.getTime() != null && !postRq.getTime().isBlank()) {
            specification = specification.and(datesBetween(getSecondDate(postRq.getTime()),
                    LocalDateTime.now()));
        }
        if (postRq.getAuthor() != null && !postRq.getAuthor().isBlank()) {
            List<Person> persons = findPersonByNameAndLastNameContains(postRq.getAuthor());
            specification = specification.and(postAuthorsIs(persons));
        }
        if (postRq.getStatus() != null && !postRq.getStatus().equals("all")) {
            specification = specification.and(blockOrUnblock(postRq.getStatus()));
        }
        return postsRepository.findAll(specification);
    }

    private LocalDateTime getSecondDate(String time) throws TimeException {
        return switch (time) {
            case "week" -> LocalDateTime.now().minusWeeks(1);
            case "month" -> LocalDateTime.now().minusMonths(1);
            case "year" -> LocalDateTime.now().minusYears(1);
            default -> throw new TimeException("Error in field 'time'");
        };
    }

    public List<Person> findPersonByNameAndLastNameContains(String name) {
        List<Person> persons = null;
        String[] splitName = name.split("\\s+");
        if (splitName.length < 2) {
            persons = personRepository.findByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(name, name);
        }
        if (splitName.length > 1) {
            persons = personRepository.findPersonByFirstNameContainsIgnoreCaseAndLastNameContainsIgnoreCase(splitName[0], splitName[1]);
        }
        assert persons != null;
        if (persons.isEmpty() && splitName.length > 1) {
            persons = personRepository.findPersonByFirstNameContainsIgnoreCaseAndLastNameContainsIgnoreCase(splitName[1], splitName[0]);
        }
        return persons;
    }
}
