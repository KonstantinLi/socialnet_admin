package adminpanel.service.search;

import adminpanel.api.request.FindCommentRq;
import adminpanel.errors.TimeException;
import adminpanel.model.CommentEntity;
import adminpanel.model.PersonEntity;
import adminpanel.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static adminpanel.service.search.CommentSpecification.*;

@Component
@RequiredArgsConstructor
public class SearchComments {

    private final CommentRepository commentRepository;

    private final CommonSearchMethods commonSearchMethods;

    public List<CommentEntity> findComments(FindCommentRq commentRq) throws TimeException {
        Specification<CommentEntity> specification = getAll();
        if (commentRq.getText() != null && !commentRq.getText().isBlank()) {
            specification = specification.and(textLike(commentRq.getText()));
        }
        if (commentRq.getTime() != null && !commentRq.getTime().isBlank()) {
            specification = specification.and(datesBetween(commonSearchMethods.getSecondDate(commentRq.getTime()),
                    LocalDateTime.now()));
        }
        if (commentRq.getAuthor() != null && !commentRq.getAuthor().isBlank()) {
            List<PersonEntity> persons = commonSearchMethods.findPersonByNameAndLastNameContains(commentRq.getAuthor());
            specification = specification.and(commentAuthorsIs(persons));
        }
        if (commentRq.getStatus() != null && !commentRq.getStatus().equals("all")) {
            specification = specification.and(blockOrUnblock(commentRq.getStatus()));
        }
        return commentRepository.findAll(specification);
    }
}
