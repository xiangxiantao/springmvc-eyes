package com.xxt.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class AppController {

    @Autowired
    @Qualifier("rootMap")
    Map<String,String> rootMap;

    @ResponseBody
    @GetMapping("/app/h")
    public String app() {
        System.out.println("hello");
        return rootMap.get("root");
    }

    /**
     * 重定向，url改变
     * @return
     */
    @GetMapping("/app/re")
    public String re() {
        System.out.println("re");
        return "redirect:/app/h";
    }

    /**
     * 转发，url不变
     * @return
     */
    @GetMapping("/app/fo")
    public String fo() {
        System.out.println("fo");
        return "forward:/app/h";
    }
}
