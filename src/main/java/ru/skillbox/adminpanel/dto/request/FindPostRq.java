package ru.skillbox.adminpanel.dto.request;

import lombok.Data;


@Data
public class FindPostRq {

    private String text;

    private String time;

    private String author;

    private String status;
}
