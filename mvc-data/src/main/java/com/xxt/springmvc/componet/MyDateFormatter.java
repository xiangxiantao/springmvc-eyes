package com.xxt.springmvc.componet;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class MyDateFormatter implements Formatter<LocalDate> {

    private final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate parse(String s, Locale locale) throws ParseException {
        System.out.println("locadate formatter parsing...");
        LocalDate localDate =LocalDate.parse(s,dateFormatter);
        return localDate;
    }

    @Override
    public String print(LocalDate localDate, Locale locale) {
        return "hhhhhhhh" + localDate.format(dateFormatter);
    }
}
