package adminpanel.api.request;

import lombok.Data;

@Data
public class FindCommentRq {

    private String text;

    private String time;

    private String author;

    private String status;
}
