package adminpanel.mappers;

import adminpanel.model.RoleEntity;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class RoleMappersMethod {

    @Named("getPhoto")
    public String getPhoto(RoleEntity roleEntity) {
        return roleEntity.getPerson().getPhoto();
    }

    @Named("getLastName")
    public String getLastName(RoleEntity roleEntity) {
        return roleEntity.getPerson().getLastName();
    }

    @Named("getFirstName")
    public String getFirstName(RoleEntity roleEntity) {
        return roleEntity.getPerson().getFirstName();
    }
}
