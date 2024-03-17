package com.BrainWorks.Plans.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "plan-api")
@Configuration
public class AppProperties {

    Map<String,String> messages =new HashMap<>();
}
