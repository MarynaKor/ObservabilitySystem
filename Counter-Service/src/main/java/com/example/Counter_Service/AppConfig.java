package com.example.Counter_Service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    /*@Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}*/

    @Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }
}

