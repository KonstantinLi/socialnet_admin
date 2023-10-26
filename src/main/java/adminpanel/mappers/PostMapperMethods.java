package adminpanel.mappers;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class PostMapperMethods {

    @Named("getPostTime")
    public String getPostTime(LocalDateTime time) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return time.format(format);
    }
}
