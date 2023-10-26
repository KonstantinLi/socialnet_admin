package adminpanel.service.search;

import adminpanel.model.PersonEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;

public class PersonSpecification {

    public static Specification<PersonEntity> excludeMe(Long id) {
        return (root, query, cb) -> cb.notEqual(root.get("id"), id);
    }

    public static Specification<PersonEntity> nameContains(String name) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("firstName")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<PersonEntity> lastNameContains(String lastName) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%");
    }

    public static Specification<PersonEntity> onlyNameInRequest(List<Long> usersIds) {
        return (root, query, cb) -> cb.in(root.get("id")).value(usersIds);
    }

    public static Specification<PersonEntity> ageFrom(Integer ageFrom) {
        LocalDateTime dateTimeFrom = LocalDateTime.now().minusYears(ageFrom);
        return (root, query, cb) -> cb.lessThan(root.get("birthDate"), dateTimeFrom);
    }

    public static Specification<PersonEntity> ageTo(Integer ageTo) {
        LocalDateTime dateTimeTo = LocalDateTime.now().minusYears(ageTo);
        return (root, query, cb) -> cb.greaterThan(root.get("birthDate"), dateTimeTo);
    }

    public static Specification<PersonEntity> personCountry(String country) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("country")), "%" + country.toLowerCase() + "%");
    }

    public static Specification<PersonEntity> personCity(String city) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("city")), "%" + city.toLowerCase() + "%");
    }

    public static Specification<PersonEntity> blockOrUnblock(String block) {
        boolean blockUnblock = block.equals("true");
        return ((root, query, cb) -> cb.equal(root.get("isBlocked"), blockUnblock));
    }

    public static Specification<PersonEntity> getAll() {
        return ((root, query, cb) -> cb.isNotNull(root.get("id")));
    }
}
