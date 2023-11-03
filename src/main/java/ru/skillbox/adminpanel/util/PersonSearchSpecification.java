package ru.skillbox.adminpanel.util;

import org.springframework.data.jpa.domain.Specification;
import ru.skillbox.adminpanel.entity.Person;

import java.time.LocalDateTime;
import java.util.List;

public class PersonSearchSpecification {
    private PersonSearchSpecification() {
    }

    @SuppressWarnings("unused")
    public static Specification<Person> excludeMe(Long id) {
        return (root, query, cb) -> cb.notEqual(root.get("id"), id);
    }

    public static Specification<Person> nameContains(String name) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("firstName")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Person> lastNameContains(String lastName) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%");
    }

    @SuppressWarnings("unused")
    public static Specification<Person> onlyNameInRequest(List<Long> usersIds) {
        return (root, query, cb) -> cb.in(root.get("id")).value(usersIds);
    }

    public static Specification<Person> ageFrom(Integer ageFrom) {
        LocalDateTime dateTimeFrom = LocalDateTime.now().minusYears(ageFrom);
        return (root, query, cb) -> cb.lessThan(root.get("birthDate"), dateTimeFrom);
    }

    public static Specification<Person> ageTo(Integer ageTo) {
        LocalDateTime dateTimeTo = LocalDateTime.now().minusYears(ageTo);
        return (root, query, cb) -> cb.greaterThan(root.get("birthDate"), dateTimeTo);
    }

    public static Specification<Person> personCountry(String country) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("country")), "%" + country.toLowerCase() + "%");
    }

    public static Specification<Person> personCity(String city) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("city")), "%" + city.toLowerCase() + "%");
    }

    public static Specification<Person> blockOrUnblock(String block) {
        boolean blockUnblock = block.equals("true");
        return ((root, query, cb) -> cb.equal(root.get("isBlocked"), blockUnblock));
    }

    public static Specification<Person> getAll() {
        return ((root, query, cb) -> cb.isNotNull(root.get("id")));
    }
}
