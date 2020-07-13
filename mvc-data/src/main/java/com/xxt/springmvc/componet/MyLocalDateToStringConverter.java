package com.xxt.springmvc.componet;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MyLocalDateToStringConverter implements Converter<LocalDate , String> {

    private final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public String convert(LocalDate localDate) {
        return localDate.format(dateFormatter);
    }
}
