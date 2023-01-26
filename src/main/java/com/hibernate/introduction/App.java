package com.hibernate.introduction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication

// Servidor web
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args); // ponemos el nombre de la clase App.java que es la clase que tenemos
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/personas").allowedOrigins("*");
            }
        };
    }
}
