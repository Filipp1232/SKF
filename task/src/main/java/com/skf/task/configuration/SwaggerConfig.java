package com.skf.task.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;
import java.util.Arrays;


/**
 * Created on 26.12.2020
 *
 * @author Pylyp Gorpenko
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
  @Bean
  public Docket rest() {
    return new Docket(DocumentationType.SWAGGER_2)
        .directModelSubstitute(LocalDateTime.class, java.util.Date.class)
        .groupName("rest")
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
        .build();
  }
}
