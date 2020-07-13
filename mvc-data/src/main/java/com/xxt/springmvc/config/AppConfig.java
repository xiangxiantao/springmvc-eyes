package com.xxt.springmvc.config;

import com.xxt.springmvc.componet.MyLocalDateToStringConverter;
import com.xxt.springmvc.componet.MyStringToLocalDateConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashSet;
import java.util.Set;


@Configuration
@ComponentScan(basePackages = {"com.xxt.springmvc.controller"})
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {

    @Bean
    ConversionService conversionServiceFactoryBean(){
        Set<Converter> converters = new HashSet<>();
        converters.add(new MyLocalDateToStringConverter());
        converters.add(new MyStringToLocalDateConverter());
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        conversionServiceFactoryBean.setConverters(converters);
        return conversionServiceFactoryBean.getObject();
    }

}
