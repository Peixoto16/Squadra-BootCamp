package israel.squadra.bootcamp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi customApi() {
        return GroupedOpenApi.builder()
                .group("israel-api-squadra")
                .pathsToMatch("/**") // Inclui todos os endpoints
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API BOOTCAMP SQUADRA")
                        .version("1.0")
                        .description("Esta é uma API do projeto final do BootCamp da Squadra.")
                        .contact(new Contact()
                                .name("Israel Rodrigues")
                                .url("https://peixoto16.github.io/Portifolio/")
                                .email("israel1peixoto@gmail.com")));
    }

}
