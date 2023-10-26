package adminpanel.api.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonRs {

    private Long id;

    private String city;

    private String country;

    private String firstName;

    private String lastName;

    private String photo;

    private String age;

    private Boolean isBlocked;
}
