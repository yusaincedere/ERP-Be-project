package com.iknow.stocktrackingbe.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
@EnableWebMvc
public class SpringFoxConfig {

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Stock Tracking api")
                .description("iknow stock tracking Api")
                .version("0.0.5")
                .contact(new Contact("yusa", "", "yusa.incedere@iknow.com.tr"))
                .build();
    }

    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.iknow.stocktrackingbe.controller"))
                .build()
                .apiInfo(apiInfo());
    }
}
