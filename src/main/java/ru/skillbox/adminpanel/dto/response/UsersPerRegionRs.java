package ru.skillbox.adminpanel.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsersPerRegionRs {

    private String region;

    private Long countUsers;
}
