package adminpanel.service.search;

import adminpanel.api.request.FindPostRq;
import adminpanel.errors.TimeException;
import adminpanel.model.PersonEntity;
import adminpanel.model.PostEntity;
import adminpanel.repositories.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static adminpanel.service.search.PostSpecification.*;

@Component
@RequiredArgsConstructor
public class SearchPosts {

    private final PostsRepository postsRepository;

    private final CommonSearchMethods commonSearchMethods;

    public List<PostEntity> findPosts(FindPostRq postRq) throws TimeException {
        Specification<PostEntity> specification = getAll();
        if (postRq.getText() != null && !postRq.getText().isBlank()) {
            specification = specification.and(textLike(postRq.getText()));
        }
        if (postRq.getTime() != null && !postRq.getTime().isBlank()) {
            specification = specification.and(datesBetween(commonSearchMethods.getSecondDate(postRq.getTime()),
                    LocalDateTime.now()));
        }
        if (postRq.getAuthor() != null && !postRq.getAuthor().isBlank()) {
            List<PersonEntity> persons = commonSearchMethods.findPersonByNameAndLastNameContains(postRq.getAuthor());
            specification = specification.and(postAuthorsIs(persons));
        }
        if (postRq.getStatus() != null && !postRq.getStatus().equals("all")) {
            specification = specification.and(blockOrUnblock(postRq.getStatus()));
        }
        return postsRepository.findAll(specification);
    }
}
