package dev.anuradha.productservicesdec2024.configs;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced               //to achieve client side load balancing
    public RestTemplate getRestTemplate() {

        return new RestTemplate();
    }
}
