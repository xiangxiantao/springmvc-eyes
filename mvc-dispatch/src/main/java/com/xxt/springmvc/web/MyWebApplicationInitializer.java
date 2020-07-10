package com.xxt.springmvc.web;//package com.xxt.springmvc.web;
//
//import com.xxt.springmvc.config.AppConfig;
//import com.xxt.springmvc.config.RootConfig;
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRegistration;
//
//public class MyWebApplicationInitializer implements WebApplicationInitializer {
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        //实例化web容器
//        AnnotationConfigWebApplicationContext webApplicationContext =new AnnotationConfigWebApplicationContext();
//        //注册配置类
//        webApplicationContext.register(AppConfig.class);
//        webApplicationContext.register(RootConfig.class);
//        //刷新容器使配置生效
//        webApplicationContext.refresh();
//
//        //实例化一个dispatcherServlet
//        DispatcherServlet dispatcherServlet=new DispatcherServlet(webApplicationContext);
//
//        //将dispatcherServlet注册到servletContext容器中
//        ServletRegistration.Dynamic registration = servletContext.addServlet("app", dispatcherServlet);
//        //指定dispatcherServlet的启动顺序
//        registration.setLoadOnStartup(1);
//        //指定dispatcherServlet的扫描路径
//        registration.addMapping("/");
//
//    }
//}
