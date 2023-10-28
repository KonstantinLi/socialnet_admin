package ru.skillbox.adminpanel.service.search;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchPosts {

    /*private final OLDPostsRepository OLDPostsRepository;

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
        return OLDPostsRepository.findAll(specification);
    }*/
}
