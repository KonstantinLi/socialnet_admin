package adminpanel.mappers;

import adminpanel.api.response.ModeratorsRs;
import adminpanel.model.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RoleMappersMethod.class})
public interface RoleMapper {

    @Mapping(target = "photo", source = "roleEntity", qualifiedByName = "getPhoto")
    @Mapping(target = "lastName", source = "roleEntity", qualifiedByName = "getLastName")
    @Mapping(target = "firstName", source = "roleEntity", qualifiedByName = "getFirstName")
    ModeratorsRs toModeratorsRs(RoleEntity roleEntity);
}
