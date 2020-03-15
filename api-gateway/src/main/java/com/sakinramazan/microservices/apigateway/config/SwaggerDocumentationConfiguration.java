package com.sakinramazan.microservices.apigateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Primary
public class SwaggerDocumentationConfiguration implements SwaggerResourcesProvider {

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        resources.add(this.createSwaggerResource("blog-service", "/blog-service/v2/api-docs", "2.0"));
        resources.add(this.createSwaggerResource("post-service", "/post-service/v2/api-docs", "2.0"));
        resources.add(this.createSwaggerResource("comment-service", "/comment-service/v2/api-docs", "2.0"));
        return resources;
    }

    private SwaggerResource createSwaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
