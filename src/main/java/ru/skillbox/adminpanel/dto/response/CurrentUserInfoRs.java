package ru.skillbox.adminpanel.dto.response;

import lombok.Data;
import ru.skillbox.adminpanel.entity.Role;

@Data
public class CurrentUserInfoRs {

    private String name;
    private Role role;
}
