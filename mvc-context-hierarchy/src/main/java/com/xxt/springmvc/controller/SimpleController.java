package com.xxt.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {

    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        System.out.println("hir中文");
        return "中文的index";
    }

    @RequestMapping("/ecp/{id}")
    @ResponseBody
    public String other(@PathVariable String id){
        if ("1".equals(id)){
            throw new RuntimeException("sorry");
        }
        return "hello";
    }

    /**
     * 异常处理器
     * @return
     */
    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseBody
    public String ecp(){
        return "sorr111111y";
    }

}
