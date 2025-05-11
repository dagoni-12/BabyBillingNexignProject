package ru.anger.CRM.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class BrtClientConfig {

    @Value("${brt.base-url}")
    String baseUrl;

    @Bean
    public RestClient hrsRestClient() {
        return RestClient.builder()
                .baseUrl("http://localhost:8081/api/brt")
                .build();
    }
}

