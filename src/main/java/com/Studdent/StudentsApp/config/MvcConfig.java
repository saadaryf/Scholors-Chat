package com.Studdent.StudentsApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/home").setViewName("/Student/home");
        registry.addViewController("/").setViewName("/Student/home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/logout").setViewName("/Message/logout");
        registry.addViewController("/sendMsg").setViewName("/Student/sendMsg");
    }
}
