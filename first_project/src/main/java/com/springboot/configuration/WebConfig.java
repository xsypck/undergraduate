//跨域类
package com.springboot.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("../**").allowedOriginPatterns("*").allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                .allowCredentials(true).maxAge(3600);
    }
}
