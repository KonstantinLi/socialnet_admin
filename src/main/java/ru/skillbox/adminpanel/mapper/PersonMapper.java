package ru.skillbox.adminpanel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.skillbox.adminpanel.dto.response.PersonRs;
import ru.skillbox.adminpanel.entity.Person;


@SuppressWarnings("Annotator")
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface PersonMapper {

    @Mapping(target = "online", source = "onlineStatus")
    @Mapping(target = "userDeleted", source = "isDeleted", defaultValue = "false")
    @Mapping(target = "isBlocked", source = "isBlocked", defaultValue = "false")
    @Mapping(target = "messagesPermission", source = "messagePermissions")
    PersonRs personToPersonRs(Person person);
}
