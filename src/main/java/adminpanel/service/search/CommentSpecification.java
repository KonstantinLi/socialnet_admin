package adminpanel.service.search;

import adminpanel.model.CommentEntity;
import adminpanel.model.PersonEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;

public class CommentSpecification {

    public static Specification<CommentEntity> textLike(String text) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("commentText")), "%" + text.toLowerCase() + "%");
    }

    public static Specification<CommentEntity> datesBetween(LocalDateTime dateFrom, LocalDateTime dateTo) {
        return (root, query, cb) -> cb.between(root.get("time"), dateFrom, dateTo);
    }

    public static Specification<CommentEntity> commentAuthorsIs(List<PersonEntity> authors) {
        return (root, query, cb) -> cb.in(root.get("author")).value(authors);
    }

    public static Specification<CommentEntity> blockOrUnblock(String block) {
        boolean blockUnblock = block.equals("true");
        return ((root, query, cb) -> cb.equal(root.get("isBlocked"), blockUnblock));
    }

    public static Specification<CommentEntity> getAll() {
        return ((root, query, cb) -> cb.isNotNull(root.get("id")));
    }
}
