package org.example.config;

import org.example.graph.Graph;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Graph graph() {
        return new Graph();
    }
}
