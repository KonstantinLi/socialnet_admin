package ru.skillbox.adminpanel.util;

import org.springframework.data.jpa.domain.Specification;
import ru.skillbox.adminpanel.entity.Person;
import ru.skillbox.adminpanel.entity.Post;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

public class PostSearchSpecification {

    public static Specification<Post> textLike(String text) {
        return (root, query, cb) ->
                cb.or(
                cb.like(cb.lower(root.get("postText")), "%" + text.toLowerCase() + "%"),
                cb.like(cb.lower(root.get("title")), "%" + text.toLowerCase() + "%"));
    }

    public static Specification<Post> datesBetween(LocalDateTime dateFrom, LocalDateTime dateTo) {
        return (root, query, cb) -> cb.between(root.get("time"), dateFrom, dateTo);
    }

    public static Specification<Post> postAuthorsIs(List<Person> authors) {
        return (root, query, cb) -> cb.in(root.get("author")).value(authors);
    }

    public static Specification<Post> excludeBlockedPosts(List<Person> personsWhoBLockedMe) {
        return (root, query, cb) -> cb.in(root.get("author")).value(personsWhoBLockedMe).not();
    }

    public static Specification<Post> blockOrUnblock(String block) {
        boolean blockUnblock = block.equals("true");
        return ((root, query, cb) -> cb.equal(root.get("isBlocked"), blockUnblock));
    }

    public static Specification<Post> getAll() {
        return ((root, query, cb) -> cb.isNotNull(root.get("id")));
    }
}
