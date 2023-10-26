package adminpanel.mappers;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PersonMapperMethods {

    @Named("getPersonAge")
    public Integer getPersonAge(LocalDateTime birthdate) {
        int years = LocalDateTime.now().getYear() - birthdate.getYear();
        if (LocalDateTime.now().getMonth().getValue() - birthdate.getMonth().getValue() < 0) {
            years = years - 1;
        } else if (LocalDateTime.now().getMonth().getValue() - birthdate.getMonth().getValue() == 0 &&
                LocalDateTime.now().getDayOfMonth() - birthdate.getDayOfMonth() < 0) {
            years = years - 1;
        }
        return years;
    }
}
