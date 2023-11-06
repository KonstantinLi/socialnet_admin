package ru.skillbox.adminpanel.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.skillbox.adminpanel.dto.request.FindCommentRq;
import ru.skillbox.adminpanel.entity.Person;
import ru.skillbox.adminpanel.entity.PostComment;
import ru.skillbox.adminpanel.exception.TimeException;
import ru.skillbox.adminpanel.repository.PersonRepository;
import ru.skillbox.adminpanel.repository.PostCommentsRepository;

import java.time.LocalDateTime;
import java.util.List;

import static ru.skillbox.adminpanel.util.CommentSearchSpecification.*;

@Component
@RequiredArgsConstructor
public class CommentSearchExecutor {

    private final PostCommentsRepository commentRepository;
    private final PersonRepository personRepository;


    public List<PostComment> findComments(FindCommentRq commentRq) throws TimeException {
        Specification<PostComment> specification = getAll();
        if (commentRq.getText() != null && !commentRq.getText().isBlank()) {
            specification = specification.and(textLike(commentRq.getText()));
        }
        if (commentRq.getTime() != null && !commentRq.getTime().isBlank()) {
            specification = specification.and(datesBetween(getSecondDate(commentRq.getTime()),
                    LocalDateTime.now()));
        }
        if (commentRq.getAuthor() != null && !commentRq.getAuthor().isBlank()) {
            List<Person> persons = personRepository.findPersonByNameAndLastNameContains(commentRq.getAuthor());
            specification = specification.and(commentAuthorsIs(persons));
        }
        if (commentRq.getStatus() != null && !commentRq.getStatus().equals("all")) {
            specification = specification.and(blockOrUnblock(commentRq.getStatus()));
        }
        return commentRepository.findAll(specification);
    }

    private LocalDateTime getSecondDate(String time) throws TimeException {
        return switch (time) {
            case "week" -> LocalDateTime.now().minusWeeks(1);
            case "month" -> LocalDateTime.now().minusMonths(1);
            case "year" -> LocalDateTime.now().minusYears(1);
            default -> throw new TimeException("Error in field 'time'");
        };
    }
}
