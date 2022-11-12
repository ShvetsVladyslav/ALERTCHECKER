package com.project.alertcheker.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    ObjectMapper customObjectMapper() {
        return new ObjectMapper();
    }
}
