package com.doctor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig {

    @Value("${cors.allowed-origins}")
    private String[] allowedOrigins;

    @Value("${cors.max-age:3600}")
    private long maxAge;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Set allowed origins from configuration
        Arrays.stream(allowedOrigins).forEach(config::addAllowedOrigin);

        // Standard configuration
        config.setAllowedMethods(Arrays.asList(
                "OPTIONS", "GET", "POST", "PUT", "DELETE", "PATCH"
        ));

        // Recommended security headers
        config.setAllowedHeaders(Arrays.asList(
                "Origin", "Content-Type", "Accept", "Authorization",
                "X-Requested-With", "Cache-Control"
        ));

        // Expose necessary headers to frontend
        config.setExposedHeaders(Arrays.asList(
                "Authorization", "Content-Disposition"
        ));

        config.setAllowCredentials(true);
        config.setMaxAge(maxAge);

        // Apply to all endpoints
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
