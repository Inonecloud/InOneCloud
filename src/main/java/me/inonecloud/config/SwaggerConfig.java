package me.inonecloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

@EnableSwagger2

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket productApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("me.inonecloud.controller"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Collections.singletonList(securityScheme()));
    }

    private SecurityScheme securityScheme(){
        return new BasicAuth("basic_auth");
    }

    private SecurityContext securityContext(){
        return SecurityContext.builder()
                .securityReferences(
                        Arrays.asList(new SecurityReference("basicAuth",new AuthorizationScope[] { new AuthorizationScope("read", "for read op")}))
                )
                .forPaths(PathSelectors.regex(""))
                .build();
    }

//    @Bean
//    SecurityConfiguration security(){
//        return SecurityConfigurationBuilder.builder()
//                .useBasicAuthenticationWithAccessCodeGrant(true)
//                .build();
//    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "REST API of application for Interview and online University",
                "Hello there! We wrote a new service for our company. Have a fun!",
                "API v1.0",
                "Terms of service",
                new Contact("Andrew Yelmanov", "no website", "aelmanov@phoenixit.ru"),
                "License of API", "API license URL", Collections.emptyList());
    }
}