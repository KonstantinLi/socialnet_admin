package ru.skillbox.adminpanel.dto.response;

import ru.skillbox.adminpanel.exception.BadRequestException;

public class StatisticsResponseParseException extends BadRequestException {

    public StatisticsResponseParseException(String message) {
        super(message);
    }
}
