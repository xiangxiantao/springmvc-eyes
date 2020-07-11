package com.xxt.springmvc.web;

import com.xxt.springmvc.config.AppConfig;
import com.xxt.springmvc.config.RootConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.annotation.processing.Filer;
import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.EnumSet;

/**
 * 配置并初始化DispatcherServlet
 */
public class MyWebApplicationinitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        //创建spring web容器
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        //注入配置类
        applicationContext.register(AppConfig.class);
        applicationContext.register(RootConfig.class);
        //刷新容器使注入的配置类生效
        applicationContext.refresh();

        //创建dispatcherServlet
        DispatcherServlet dispatcherServlet =new DispatcherServlet(applicationContext);
        //利用servlet接口向web容器中注册dispatcherServlet
        ServletRegistration.Dynamic registration = servletContext.addServlet("dispServlet", dispatcherServlet);
        registration.setLoadOnStartup(1);
        //设置dispatcherServlet的拦截路径
        registration.addMapping("/");

        //添加字符编码过滤器
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        javax.servlet.FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("charsetFilter" , characterEncodingFilter);
        filterRegistration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE), false, "/");

    }
}
