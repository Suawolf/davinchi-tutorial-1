package edu.tutorial.davinchi1.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot Web API Davinchi",
//                version = "${api.version}",
                version = "0.9.20",
                contact = @Contact(
                        name = "Suawolf",
                        email = "lcgswolf@gmail.com",
                        url = "https://github.com/Suawolf"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
//                ),
//                termsOfService = "${tos.uril}",
//                description = "${api.description}"
        ),
        servers = @Server(
//                url = "${api.server.url}",
                url = "http://localhost:8080",
                description = "Production"
        )
)
@Configuration
public class OpenAPIConfig {
}
