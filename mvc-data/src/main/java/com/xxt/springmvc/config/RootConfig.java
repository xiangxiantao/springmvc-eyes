package com.xxt.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = {"com.xxt.springmvc"} )
public class RootConfig {

    @Bean("rootMap")
    public Map<String, String> map() {
        Map<String, String> map = new HashMap<>();
        map.put("root", "你好");
        return map;
    }

}
