package com.example.servlet_filter_listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ServletFilterListenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServletFilterListenerApplication.class, args);
    }

}
