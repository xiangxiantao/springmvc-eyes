package com.xxt.springmvc.controller;

import com.xxt.springmvc.entity.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result jsonErrorHandler(Exception e){
        return new Result("500",e.getMessage());
    }

}
