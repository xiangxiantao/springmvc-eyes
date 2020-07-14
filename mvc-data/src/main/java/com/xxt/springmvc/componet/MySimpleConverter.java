package com.xxt.springmvc.componet;

import org.springframework.core.convert.converter.Converter;

public class MySimpleConverter implements Converter<String,String> {
    @Override
    public String convert(String s) {
        System.out.println("have changed");
        return null;
    }
}
