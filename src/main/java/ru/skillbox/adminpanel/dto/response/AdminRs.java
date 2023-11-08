package ru.skillbox.adminpanel.dto.response;

import lombok.Builder;
import lombok.Data;
import ru.skillbox.adminpanel.entity.Role;

@Data
@Builder
public class AdminRs {

    private Long id;

    private String adminLogin;

    private Role role;
}
