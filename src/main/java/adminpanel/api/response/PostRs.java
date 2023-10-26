package adminpanel.api.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostRs {

    private Long id;

    private PersonRs author;

    private String title;

    private String postText;

    private Boolean isBlocked;

    private String time;

}
