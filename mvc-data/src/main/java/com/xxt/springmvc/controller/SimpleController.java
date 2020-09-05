package com.xxt.springmvc.controller;

import com.xxt.springmvc.componet.MyDateFormatter;
import com.xxt.springmvc.componet.MySimpleConverter;
import com.xxt.springmvc.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

@Controller
public class SimpleController {

    /**
     *  针对该controller注册formatter,并优先使用
     *  WebDataBinder不需要我们自己去创建，我们只需要向它注册参数类型对应的属性编辑器PropertyEditor。
     *  PropertyEditor可以将字符串转换成其真正的数据类型，它的void setAsText(String text)方法实现数据转换的过程
     * @param webDataBinder
     */
    @InitBinder
    public void initDataBinder(WebDataBinder webDataBinder){
        webDataBinder.addCustomFormatter(new MyDateFormatter());
        FormattingConversionService conversionService = (FormattingConversionService)webDataBinder.getConversionService();
        conversionService.addConverter(new MySimpleConverter());
    }


    @RequestMapping("/getPeRsonByString")
    @ResponseBody
    public String getPersonFromString(@RequestParam("person") Person person){
        System.out.println(person);
        return person.toString();
    }

    @RequestMapping("/app/index")
    @ResponseBody
    public String index(){
        System.out.println("app中文");
        return "中文的index";
    }

    @ResponseBody
    @RequestMapping("/person")
    public String getPerson(Person person){
        System.out.println(person);
        return person.toString();
    }

    @ResponseBody
    @RequestMapping("/personJson")
    public Person getPersonJson(Person person){
        System.out.println(person);
        if (person.getAge()==-1){
            throw new RuntimeException("年龄范围异常");
        }
        return person;
    }

    @ResponseBody
    @RequestMapping("/birth")
    public LocalDate birth(Person person){
        System.out.println(person);
        return person.getBirth();
    }

    @RequestMapping("/index")
    public String idx(){
        return "index";
    }

}
