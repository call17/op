package ru.sbertech.datahub.ui.config;

import com.vaadin.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringComponent
public class Constant {

    @Value("${backend.url}")
    public String BACKEND_URL = "http://localhost:8080/as-metrica";

    @Value("${webservice.url}")
    public String WEB_SERVICE_URL = "http://localhost:29001/";
}
