package com.xxt.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {

    @RequestMapping("/app/index")
    @ResponseBody
    public String index(){
        System.out.println("app中文");
        return "中文的index";
    }

}
