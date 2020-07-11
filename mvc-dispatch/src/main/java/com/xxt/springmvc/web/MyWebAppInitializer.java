package com.xxt.springmvc.web;

import com.xxt.springmvc.config.AppConfig;
import com.xxt.springmvc.config.RootConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        System.out.println("注册RootConfig到父容器");
        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        System.out.println("注册AppConfig到子容器");
        return new Class[]{AppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        System.out.println("dispatcherServlet拦截路径：/");
        return new String[]{"/"};
    }


    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        //servlet3.0文件上传
        registration.setMultipartConfig(new MultipartConfigElement("/tmp"));
        //详细日志记录
        registration.setInitParameter("enableLoggingRequestDetails", "true");
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("utf8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] {characterEncodingFilter};
    }
}
