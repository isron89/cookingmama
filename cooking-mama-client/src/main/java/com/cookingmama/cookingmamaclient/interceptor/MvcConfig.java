package com.cookingmama.cookingmamaclient.interceptor;

import com.cookingmama.cookingmamaclient.interceptor.LoggerInterceptor;
//import com.cookingmama.cookingmamaclient.interceptor.SessionTimerInterceptor;
//import com.cookingmama.cookingmamaclient.interceptor.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

//@EnableWebMvc
@Configuration
@ComponentScan("com.cookingmama.cookingmamaclient.controller")
public class MvcConfig implements WebMvcConfigurer {

    public MvcConfig() {
        super();
    }
    // API
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController("/create.html");
        registry.addViewController("/detailrecipe.html");
        registry.addViewController("/edit.html");
        registry.addViewController("/home.html");
        registry.addViewController("/login.html");
        registry.addViewController("/private.html");
        registry.addViewController("/register.html");
    }

//    @Bean
//    public ViewResolver viewResolver() {
//        final InternalResourceViewResolver bean = new InternalResourceViewResolver();
//
//        bean.setViewClass(JstlView.class);
//        bean.setPrefix("/WEB-INF/view/");
//        bean.setSuffix(".jsp");
//
//        return bean;
//    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor());
//        registry.addInterceptor(new UserInterceptor());
//        registry.addInterceptor(new SessionTimerInterceptor());
    }
}