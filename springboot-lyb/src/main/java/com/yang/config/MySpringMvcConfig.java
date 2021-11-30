package com.yang.config;


import org.apache.coyote.http11.filters.VoidInputFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MySpringMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/home").setViewName("index");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/note").setViewName("note");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        List<String> list = new ArrayList<String>(){
            {
                add("/js/**");
                add("/css/**");
                add("/img/**");
                add("/*.html");
            }
        };
        List<String> exlist = new ArrayList<String>(){
            {
                add("/note");
                add("/_fragments.html");
                add("/login");
                add("/login.html");
                add("/user/login");
                add("/index.html");
                add("/nest.html");
                add("/register");
                add("/user/checkEmail");
                add("/user/getCheckCode");
                add("/user/checkUserName");
                add("/user/checkCheckCode");
                add("/user/register");
            }
        };
        registry.addInterceptor(new LoginHandlerInterceptor()).excludePathPatterns(list).excludePathPatterns(exlist);
    }



}
