package com.xxt.springmvc.controller;

import com.xxt.springmvc.componet.MyDateFormatter;
import com.xxt.springmvc.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

@Controller
public class SimpleController {

    /**
     *  WebDataBinder不需要我们自己去创建，我们只需要向它注册参数类型对应的属性编辑器PropertyEditor。
     *  PropertyEditor可以将字符串转换成其真正的数据类型，它的void setAsText(String text)方法实现数据转换的过程
     * @param webDataBinder
     */
    @InitBinder
    public void initDataBinder(WebDataBinder webDataBinder){
        webDataBinder.addCustomFormatter(new MyDateFormatter());
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
    @RequestMapping("/birth")
    public LocalDate birth(Person person){
        System.out.println(person);
        return person.getBirth();
    }

}
