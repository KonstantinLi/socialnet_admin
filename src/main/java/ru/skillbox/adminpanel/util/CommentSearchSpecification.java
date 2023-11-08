package ru.skillbox.adminpanel.util;

import org.springframework.data.jpa.domain.Specification;
import ru.skillbox.adminpanel.entity.Person;
import ru.skillbox.adminpanel.entity.PostComment;

import java.time.LocalDateTime;
import java.util.List;

public class CommentSearchSpecification {

    private CommentSearchSpecification() {
        throw new IllegalStateException("Utility class");
    }

    public static Specification<PostComment> textLike(String text) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("commentText")), "%" + text.toLowerCase() + "%");
    }

    public static Specification<PostComment> datesBetween(LocalDateTime dateFrom, LocalDateTime dateTo) {
        return (root, query, cb) -> cb.between(root.get("time"), dateFrom, dateTo);
    }

    public static Specification<PostComment> commentAuthorsIs(List<Person> authors) {
        return (root, query, cb) -> cb.in(root.get("author")).value(authors);
    }

    public static Specification<PostComment> blockOrUnblock(String block) {
        boolean blockUnblock = block.equals("true");
        return ((root, query, cb) -> cb.equal(root.get("isBlocked"), blockUnblock));
    }

    public static Specification<PostComment> getAll() {
        return ((root, query, cb) -> cb.isNotNull(root.get("id")));
    }
}
