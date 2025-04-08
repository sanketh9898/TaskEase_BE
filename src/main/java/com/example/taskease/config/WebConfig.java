package com.example.taskease.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

    @Configuration
    public class WebConfig implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/api/**") // Apply to all API endpoints under /api
                   .allowedOrigins("*") // Allow all origins (FOR DEVELOPMENT ONLY!)
                    // .allowedOrigins("http://35.170.182.117")

                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed methods
                    .allowedHeaders("*") // Allowed headers
                    .allowCredentials(false) // Set to true if cookies/credentials needed
                    .maxAge(3600); // Cache preflight response (seconds)
        }
}
