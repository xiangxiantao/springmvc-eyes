package com.xxt.springmvc.config;


import com.xxt.springmvc.componet.MyDateFormatter;
import com.xxt.springmvc.componet.MyHttpmessageConverter;
import com.xxt.springmvc.componet.MySimpleConverter;
import com.xxt.springmvc.componet.MyStringToLocalDateConverter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


@Configuration
@ComponentScan(basePackages = {"com.xxt.springmvc"})
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {

    /**
     * 在全局注册converter和formatter
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new MySimpleConverter());
        registry.addFormatter(new MyDateFormatter());
        registry.addConverter(new MyStringToLocalDateConverter());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MyHttpmessageConverter());
    }


    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/jsp/",".jsp");
    }
}
