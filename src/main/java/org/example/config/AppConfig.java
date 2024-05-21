package org.example.config;

import org.example.graph.Graph;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@ComponentScan(basePackages = "org.example")
@EnableAspectJAutoProxy

public class AppConfig {

    @Bean
    public Graph graph() {
        return new Graph();
    }
}


