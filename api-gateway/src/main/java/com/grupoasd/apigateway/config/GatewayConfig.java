/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupoasd.apigateway.config;

import com.grupoasd.apigateway.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 *
 * @author boca-
 */
@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes().route("empleados", r -> r.path("/api/1.0/empleados/**","/api/1.0/grupos/**").filters(f -> f.filter(filter)).uri("lb://empleados"))
                .route("auth", r -> r.path("/api/1.0/auth/**").filters(f -> f.filter(filter)).uri("lb://auth"))
                .route("ordenes", r -> r.path("/api/1.0/equipos/**","/api/1.0/equipos/**").filters(f -> f.filter(filter)).uri("lb://ordenes")).build();
    }
    
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

}
