package com.project.alertcheker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class AlertchekerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlertchekerApplication.class, args);
    }

}
