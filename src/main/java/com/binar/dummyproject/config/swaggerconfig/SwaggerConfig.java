package com.binar.dummyproject.config.swaggerconfig;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(@Value("dummyProject API is an API for final project. This API can perform various operations for 'second hand' things. " +
            "The dummyProject API is still under development so if you find any bugs, please report them at the contact below. " +
            "Your support means a lot to us. ")String appDescription,
                                 @Value("v.0.0.1") String appVersion){
        return new OpenAPI().info(
                new Info().title("dummyProject")
                        .version(appVersion)
                        .description(appDescription)
                        .contact(new Contact()
                                .email("adesfrdnmdn@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdocs.org"))
        );
    }
}