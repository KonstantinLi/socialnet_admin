package ru.skillbox.adminpanel.service.search;

import ru.skillbox.adminpanel.exception.TimeException;

import java.time.LocalDateTime;

public class CommonSearchMethods {
    //private final OLDPersonsRepository OLDPersonsRepository;

    //TODO interesting feature try to implement later
    public LocalDateTime getSecondDate(String time) throws TimeException {
        if (time.equals("week")) {
            return LocalDateTime.now().minusWeeks(1);
        } else if (time.equals("month")) {
            return LocalDateTime.now().minusMonths(1);
        } else if (time.equals("year")) {
            return LocalDateTime.now().minusYears(1);
        } else {
            throw new TimeException("Error in field 'time'");
        }
    }

   /* public List<PersonEntity> findPersonByNameAndLastNameContains(String name) {
        List<PersonEntity> persons = null;
        String[] splitName = name.split("\\s+");
        if (splitName.length < 2) {
            persons = OLDPersonsRepository.findPersonByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(name, name);
        }
        if (splitName.length > 1) {
            persons = OLDPersonsRepository.findPersonByFirstNameContainsIgnoreCaseAndLastNameContainsIgnoreCase(splitName[0], splitName[1]);
        }
        assert persons != null;
        if (persons.size() == 0 && splitName.length > 1) {
            persons = OLDPersonsRepository.findPersonByFirstNameContainsIgnoreCaseAndLastNameContainsIgnoreCase(splitName[1], splitName[0]);
        }
        return persons;
    }
*/
}
