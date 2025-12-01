package com.example.taskmanagerbackend.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI configuration for Swagger UI metadata and tags.
 */
@Configuration
public class OpenApiConfig {

    // PUBLIC_INTERFACE
    @Bean
    public OpenAPI taskManagerOpenAPI() {
        /**
         * Provides OpenAPI metadata for the application and configures tags.
         */
        return new OpenAPI()
                .info(new Info()
                        .title("Task Manager API")
                        .description("Simple task management API (in-memory) with CRUD operations.")
                        .version("0.1.0")
                        .contact(new Contact().name("Task Manager").email("noreply@example.com")))
                .addTagsItem(new Tag().name("Tasks").description("CRUD operations for managing tasks"))
                .addTagsItem(new Tag().name("Hello Controller").description("Basic endpoints for taskmanagerbackend"))
                .externalDocs(new ExternalDocumentation()
                        .description("Swagger UI")
                        .url("/swagger-ui.html"));
    }
}
