package com.xxt.springmvc.componet;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MyStringToLocalDateConverter implements Converter<String , LocalDate> {

    private final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate convert(String s) {
        System.out.println("converting localdate...");
        return LocalDate.parse(s,dateFormatter);
    }
}
