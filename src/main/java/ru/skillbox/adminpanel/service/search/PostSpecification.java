package ru.skillbox.adminpanel.service.search;

public class PostSpecification {

    /*public static Specification<PostEntity> textLike(String text) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("postText")), "%" + text.toLowerCase() + "%");
    }

    public static Specification<PostEntity> datesBetween(LocalDateTime dateFrom, LocalDateTime dateTo) {
        return (root, query, cb) -> cb.between(root.get("time"), dateFrom, dateTo);
    }

    public static Specification<PostEntity> postAuthorsIs(List<PersonEntity> authors) {
        return (root, query, cb) -> cb.in(root.get("author")).value(authors);
    }

    public static Specification<PostEntity> excludeBlockedPosts(List<PersonEntity> personsWhoBLockedMe) {
        return (root, query, cb) -> cb.in(root.get("author")).value(personsWhoBLockedMe).not();
    }

    public static Specification<PostEntity> blockOrUnblock(String block) {
        boolean blockUnblock = block.equals("true");
        return ((root, query, cb) -> cb.equal(root.get("isBlocked"), blockUnblock));
    }

    public static Specification<PostEntity> getAll() {
        return ((root, query, cb) -> cb.isNotNull(root.get("id")));
    }*/
}
