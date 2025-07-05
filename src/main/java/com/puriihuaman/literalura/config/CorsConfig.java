package com.puriihuaman.literalura.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {
    /**
     * List of origins allowed accessing API resources.
     * Only requests from these domains will be accepted.
     */
    private static final List<String> ORIGINS = List.of(
            "http://localhost:4200",
            "http://localhost:4321",
            "http://localhost:5173",
            "http://localhost:8080"
    );
    
    /**
     * List of HTTP methods allowed in API requests.
     * The CORS policy will reject requests using other methods.
     */
    private static final List<String> METHODS = List.of(
            HttpMethod.GET.name(),
            HttpMethod.POST.name(),
            HttpMethod.PUT.name(),
            HttpMethod.DELETE.name(),
            HttpMethod.PATCH.name()
    );
    
    /**
     * List of allowed headers that clients can include in their API requests.
     * CORS policy may reject any header not listed here.
     */
    private static final List<String> HEADERS = List.of(
            HttpHeaders.AUTHORIZATION,
            HttpHeaders.CONTENT_TYPE,
            HttpHeaders.ACCEPT
    );
    
    /**
     * Defines the CORS configuration source for the application.
     * This bean configures the CORS policies that will be applied to incoming requests to routes
     * under the pattern "/api/**"
     *
     * @return {@link CorsConfigurationSource} configured with the origins, methods,
     * allowed headers, credential enablement, and maximum preflight response time (in seconds).
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        
        corsConfig.setAllowedOrigins(ORIGINS);
        corsConfig.setAllowedMethods(METHODS);
        corsConfig.setAllowedHeaders(HEADERS);
        corsConfig.setAllowCredentials(true);
        corsConfig.setMaxAge(3600L);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", corsConfig);
        
        return source;
    }
}