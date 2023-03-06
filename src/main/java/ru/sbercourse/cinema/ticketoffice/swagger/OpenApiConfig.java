package ru.sbercourse.cinema.ticketoffice.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    //http://localhost:8080/swagger-ui/index.html#/
    @Bean
    public OpenAPI TicketOfficeProject() {
        return new OpenAPI()
                .info(new Info()
                        .title("Билетная касса кинотеатра")
                        .description("Сервис, позволяющий осуществить онлайн покупку билета в кинотеатр.")
                        .version("v0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                        .contact(new Contact().name("Vasya Pupkin")
                                .email("")
                                .url(""))
                );
    }
}
