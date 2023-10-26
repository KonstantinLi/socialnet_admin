package adminpanel.api.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MeRs {

    private String firstName;

    private String lastName;
}
