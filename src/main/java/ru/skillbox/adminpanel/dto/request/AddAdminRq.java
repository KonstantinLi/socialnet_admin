package ru.skillbox.adminpanel.dto.request;

import lombok.Data;
import ru.skillbox.adminpanel.entity.Role;

@Data
public class AddAdminRq {

    private String email;
    private Role role;
    private String password;
}
