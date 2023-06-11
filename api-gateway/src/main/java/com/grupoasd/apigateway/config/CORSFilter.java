/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupoasd.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * @author Dhiraj Ray
 *
 */
@Configuration
@EnableWebFlux
public class CORSFilter implements WebFluxConfigurer {

   @Override
   public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**")
            .allowCredentials(true)
            .allowedOriginPatterns("http://localhost:4200")
            .allowedHeaders("Content-Type")
            .allowedMethods("GET, POST, PUT, DELETE, OPTIONS");
   }

   @Bean
   public CorsWebFilter corsWebFilter() {
      CorsConfiguration corsConfiguration = new CorsConfiguration();
      corsConfiguration.setAllowCredentials(false);
      corsConfiguration.addAllowedHeader("*");
      corsConfiguration.addAllowedMethod("*");
      corsConfiguration.addAllowedOriginPattern("*");
      UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
      corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
      return new CorsWebFilter(corsConfigurationSource);
   }
}