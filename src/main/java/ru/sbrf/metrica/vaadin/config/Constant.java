package ru.sbrf.metrica.vaadin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Constant {

    @Value("${backend.url}")
    public String BACKEND_URL;
}
