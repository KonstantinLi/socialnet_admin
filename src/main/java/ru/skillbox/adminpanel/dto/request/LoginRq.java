package ru.skillbox.adminpanel.dto.request;

import lombok.Data;

@Data
public class LoginRq {

    private String email;

    private String password;
}
