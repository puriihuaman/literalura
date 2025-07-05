package com.puriihuaman.literalura.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Literalura API", version = "1.0.0",
                description = "Rest API that allows you to manage books, authors, and translators efficiently and securely.",
                termsOfService = "https://swagger.io/terms/", contact = @Contact(
                name = "Pedro Purihuaman", url = "https://puriihuaman.netlify.app/",
                email = "pedropuriihuaman@gmail.com"
        )
        ), servers = {
        @Server(description = "DEV SERVER", url = "http://localhost:8080/api"),
        @Server(description = "PROD SERVER", url = "https://example:8080/api")
}
)
public class SwaggerConfig {}