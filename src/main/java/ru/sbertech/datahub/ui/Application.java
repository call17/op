package ru.sbertech.datahub.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootApplication
@WebAppConfiguration
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationUI.ApplicationServlet.class, args);
	}

	private static Class<Application> applicationClass = Application.class;

//    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder) {
        return springApplicationBuilder.sources(applicationClass);
    }
}
