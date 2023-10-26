package adminpanel.api.response;

import adminpanel.model.enums.Roles;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModeratorsRs {

    private Long id;

    private String firstName;

    private String lastName;

    private String photo;

    private Roles role;

}
