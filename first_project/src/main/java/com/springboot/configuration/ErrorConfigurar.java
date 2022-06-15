package com.springboot.configuration;

import com.springboot.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ErrorConfigurar implements WebMvcConfigurer {
    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new UserInterceptor())
                .excludePathPatterns("/user/findOne").excludePathPatterns("/user/add")
                .excludePathPatterns("/image/**").excludePathPatterns("/image/test/**")
                .excludePathPatterns("/image/patrol/**").excludePathPatterns("/four/preview")
                .excludePathPatterns("/favicon.ico").excludePathPatterns("/dist/**");
    }
   
}
