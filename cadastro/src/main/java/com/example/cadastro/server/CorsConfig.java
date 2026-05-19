package com.example.cadastro.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // todas as rotas da API
                        .allowedOrigins(
                                "http://localhost:8081",   // front rodando no navegador
                                "http://10.0.2.2:8081",    // emulador Android
                                "http://127.0.0.1:8081",   // emulador iOS
                                "http://192.168.0.10:8081" // exemplo: IP da máquina na rede
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
