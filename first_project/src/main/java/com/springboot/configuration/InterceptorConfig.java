package com.springboot.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //本地资源虚拟路径
        registry.addResourceHandler("/image/**").addResourceLocations("file:/home/historyImgs/");
        registry.addResourceHandler("/image/test/**").addResourceLocations("file:/home/testImgs/");
        registry.addResourceHandler("/image/patrol/**").addResourceLocations("file:/home/patrolImgs/");
        registry.addResourceHandler("/files/**").addResourceLocations("file:/home/files/");
        registry.addResourceHandler("/dist/**").addResourceLocations("classpath:/static/dist/");
    }

}
