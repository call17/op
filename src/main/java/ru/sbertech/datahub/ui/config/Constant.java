package ru.sbertech.datahub.ui.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Constant {

    @Value("${backend.url}")
    public String BACKEND_URL;

    @Value("${webservice.url}")
    public String WEB_SERVICE_URL;
}
