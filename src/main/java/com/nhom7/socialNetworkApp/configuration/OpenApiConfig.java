package com.nhom7.socialNetworkApp.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "nhom7"
                ),
                description = "Open API document",
                title = "OpenApi specification",
                version = "1.0",
                license = @License(
                        name = "Apache 2.0",
                        url = "https://springdoc.org"
                )
        ),
        servers = {
                @Server(
                        description = "local ENV",
                        url = "http://localhost:8888"
                )
        }
)
public class OpenApiConfig {
}
