package com.BrainWorks.CO_API.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi controllerApi(){
        return GroupedOpenApi.builder()
                .packagesToScan("com.BrainWorks.CO_API.rest")
                .group("ED_API controller")
                .build();
    }
}
