package com.grupoasd.gestionauth.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.grupoasd.gestionauth.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Auth Service API",
                "Servicios para la gestión de autenticaciones",
                "1.0",
                "https://www.grupoasd.com/politica-de-privacidad/",
                new Contact("Grupo ASD", "https://www.grupoasd.com/", "https://www.grupoasd.com/"),
                "LICENSE",
                "LICENSE URL",
                Collections.emptyList()
        );
    }
}
