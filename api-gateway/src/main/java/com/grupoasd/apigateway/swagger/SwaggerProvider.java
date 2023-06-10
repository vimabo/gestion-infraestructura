/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupoasd.apigateway.swagger;

import java.util.ArrayList;
import java.util.List;


import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
/**
 *
 * @author boca-
 */
@Component
@Primary
@Slf4j
public class SwaggerProvider implements SwaggerResourcesProvider {
    
    public static final String API_URI = "/v2/api-docs";
    
    private final RouteDefinitionLocator routeLocator;
    private static final String API_GATEWAY_PREDICATE = "api-gateway";
    
    public SwaggerProvider(RouteDefinitionLocator routeLocator) {
        this.routeLocator = routeLocator;
    }
    
    @Override
    public List<SwaggerResource> get() {
        String gatewayPredicate = "/" + API_GATEWAY_PREDICATE;
        List<SwaggerResource> resources = new ArrayList<>();
        routeLocator.getRouteDefinitions().subscribe(routeDefinition -> {
            log.info("Discovered route definition: {}", routeDefinition.getId());
            String resourceName = routeDefinition.getId();
            String location = routeDefinition.getPredicates().get(0).getArgs().get("_genkey_0").replace("/**", API_URI);

            if (location.contains(gatewayPredicate)) {
                location = location.replace(gatewayPredicate, "");
            }

            log.info("Adding swagger resource: {} with location {}", resourceName, location);
            resources.add(swaggerResource(resourceName, location));
        });
        
        return resources;
    }
    
    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;    
    }
}