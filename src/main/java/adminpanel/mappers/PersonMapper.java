package adminpanel.mappers;

import adminpanel.api.response.MeRs;
import adminpanel.api.response.PersonRs;
import adminpanel.model.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PersonMapperMethods.class})
public interface PersonMapper {

    @Mapping(target = "age", source = "birthDate", qualifiedByName = "getPersonAge")
    PersonRs toPersonRs(PersonEntity person);

    MeRs toMeRs(PersonEntity person);
}
