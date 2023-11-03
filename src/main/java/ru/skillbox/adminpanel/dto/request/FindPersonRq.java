package ru.skillbox.adminpanel.dto.request;

import lombok.Data;

@Data
public class FindPersonRq {

    private String firstName;

    private String lastName;

    private Integer ageFrom;

    private Integer ageTo;

    private String city;

    private String country;

    private String status;
}
