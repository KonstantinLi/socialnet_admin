package ru.skillbox.adminpanel.dto.response;

import lombok.Builder;
import lombok.Data;
import ru.skillbox.adminpanel.entity.Role;

@Data
@Builder
public class ModeratorsRs {

    private Long id;

    private String firstName;

    private String lastName;

    private String photo;

    private Role role;

}
