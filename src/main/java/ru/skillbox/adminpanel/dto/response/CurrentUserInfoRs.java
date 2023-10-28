package ru.skillbox.adminpanel.dto.response;

import lombok.Data;

@Data
public class CurrentUserInfoRs {

    private String name;

    public CurrentUserInfoRs(String name) {
        this.name = name;
    }
}
