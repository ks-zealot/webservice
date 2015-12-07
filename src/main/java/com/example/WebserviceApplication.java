package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan({"com.example.controller", "com.example.service", "com.example.dao", "com.example.validation"})
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration.class} )
@EnableJpaRepositories
@EnableWebMvc
@ImportResource("classpath:applicationContext.xml")
public class WebserviceApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WebserviceApplication.class, args);
    }
}
