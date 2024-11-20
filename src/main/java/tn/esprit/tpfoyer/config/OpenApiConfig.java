package tn.esprit.tpfoyer.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Project Documentation")
                        .description("DEVOPS")
                        .contact(new Contact()
                                .name("DevOps Team")
                                .email("devops@example.com")
                                .url("https://example.com")
                        )
                );
    }
}
