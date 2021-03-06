package com.xxt.springmvc.config;

import com.xxt.springmvc.web.MyHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
@ComponentScan(basePackages = {"com.xxt.springmvc.controller"})
@EnableWebMvc
/**
 * extends WebMvcConfigurationSupport的效果：自定义的配置不生效，仍然使用@EnableWebMvc的默认配置
 */
public class AppConfig extends WebMvcConfigurationSupport {

    /**
     * 向容器中注册一个拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyHandlerInterceptor());
    }


}
