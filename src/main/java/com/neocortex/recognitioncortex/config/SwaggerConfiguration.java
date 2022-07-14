package com.neocortex.recognitioncortex.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(
                        new ApiInfoBuilder()
                                .description("Gestion de Formation Api documentation")
                                .title("Gestion de Formation REST API")
                                .build()
                )
                .groupName("Rest Api")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sb.formation"))
                .paths(PathSelectors.ant("localhost:8090"+"/**"))
                .build()
                .securitySchemes(Arrays.asList(apiKey()));

    }


    private ApiKey apiKey() {
        return new ApiKey("jwtToken", "Authorization", "header");
    }

}
