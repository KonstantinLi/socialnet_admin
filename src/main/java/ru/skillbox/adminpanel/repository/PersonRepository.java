package ru.skillbox.adminpanel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skillbox.adminpanel.dto.response.RegionStatisticRs;
import ru.skillbox.adminpanel.entity.Person;
import ru.skillbox.adminpanel.exception.PersonNotFoundException;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {
    Optional<Person> findByEmail(String email);

    List<Person> findByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(String firstName, String lastName);

    List<Person> findPersonByFirstNameContainsIgnoreCaseAndLastNameContainsIgnoreCase(String s, String s1);

    default List<Person> findPersonByNameAndLastNameContains(String name) {
        List<Person> persons = null;
        String[] splitName = name.split("\\s+");
        if (splitName.length < 2) {
            persons = this.findByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(name, name);
        }
        if (splitName.length > 1) {
            persons = this.findPersonByFirstNameContainsIgnoreCaseAndLastNameContainsIgnoreCase(splitName[0], splitName[1]);
        }
        assert persons != null;
        if (persons.isEmpty() && splitName.length > 1) {
            persons = this.findPersonByFirstNameContainsIgnoreCaseAndLastNameContainsIgnoreCase(splitName[1], splitName[0]);
        }
        return persons;
    }
}
