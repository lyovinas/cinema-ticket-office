package ru.sbercourse.cinema.ticketoffice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdditionalResourceWebConfig implements WebMvcConfigurer {

    @Value("${posters.path}")
    private String postersPath;

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/posters/**").addResourceLocations("file:" + postersPath + "/");
    }
}
