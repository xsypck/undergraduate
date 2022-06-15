package com.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//springboot项目入口启动类
@SpringBootApplication //springboot核心解，主要用于开启spring自动配置，就可以扫描所有注解
//@MapperScan(basePackages = "com.springboot.mapper")//开启扫描mapper接口的包以及子目录的包,扫描DAO接口到启动类
public class FirstProjectApplication {

    //springboot项目代码必须放在Application类所在目录（com.springboot）或下级目录下
    public static void main(String[] args) {

        SpringApplication.run(FirstProjectApplication.class, args);
    }
}
