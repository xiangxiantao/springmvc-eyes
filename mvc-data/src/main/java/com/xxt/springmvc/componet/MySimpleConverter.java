package com.xxt.springmvc.componet;

import com.xxt.springmvc.entity.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MySimpleConverter implements Converter<String, Person> {

    @Override
    public Person convert(String s) {
        if (StringUtils.isEmpty(s)){
            return null;
        }
        String[] split = s.split("-");
        Person person = new Person();
        person.setName(split[0]);
        person.setAge(Integer.parseInt(split[1]));
        person.setBirth(LocalDate.parse(split[2], DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        System.out.println("convert over");
        return person;
    }
}
