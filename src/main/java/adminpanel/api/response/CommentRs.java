package adminpanel.api.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentRs {

    private Long id;

    private PersonRs author;

    private String commentText;

    private Boolean isBlocked;

    private String time;
}
