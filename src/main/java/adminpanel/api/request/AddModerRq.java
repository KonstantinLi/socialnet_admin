package adminpanel.api.request;

import adminpanel.model.enums.Roles;
import lombok.Data;

@Data
public class AddModerRq {

    private String email;

    private Roles role;
}
