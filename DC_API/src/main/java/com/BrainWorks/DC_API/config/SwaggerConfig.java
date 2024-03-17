package com.BrainWorks.DC_API.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class SwaggerConfig {
    @Bean
    public GroupedOpenApi controllerApi()
    {
        return GroupedOpenApi.builder()
                .packagesToScan("com.BrainWorks.DC_API.rest")
                .group("Dc_API Controller group")
                .build();
    }
}
