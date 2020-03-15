package com.sakinramazan.microservices.blogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableFeignClients
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
@EnableSwagger2
public class BlogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogServiceApplication.class, args);
    }

}
