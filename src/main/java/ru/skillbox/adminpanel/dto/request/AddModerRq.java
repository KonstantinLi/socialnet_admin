package ru.skillbox.adminpanel.dto.request;

import lombok.Data;
import ru.skillbox.adminpanel.entity.Role;

@Data
public class AddModerRq {

    private String email;

    private Role role;
}
