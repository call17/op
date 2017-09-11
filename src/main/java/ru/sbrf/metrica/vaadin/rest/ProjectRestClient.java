package ru.sbrf.metrica.vaadin.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sbrf.metrica.vaadin.config.Constant;
import ru.sbrf.metrica.vaadin.model.Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProjectRestClient {

    @Autowired
    Constant constant;
    @Autowired
    RestTemplate restTemplate;
    private String PROJECT_PATH = "/api/chart/projects";

    public List<Project> getProjects() {
        List<Project> projects = new ArrayList<>();

        ResponseEntity<Project[]> response = restTemplate.getForEntity(constant.BACKEND_URL + PROJECT_PATH, Project[].class);
        projects.addAll(Arrays.asList(response.getBody()));

        return projects;
    }

    public void saveProject(Project project) {
        restTemplate.postForEntity(constant.BACKEND_URL + PROJECT_PATH, new HttpEntity<>(project), Object.class);
    }

}
