package ru.sbertech.datahub.ui.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sbertech.datahub.ui.config.Constant;

@Service
public class ProjectRestClient {

    @Autowired
    Constant constant;
    @Autowired
    RestTemplate restTemplate;
    private String PROJECT_PATH = "/api/chart/projects";

//    public List<Project> getProjects() {
//        List<Project> projects = new ArrayList<>();
//
//        ResponseEntity<Project[]> response = restTemplate.getForEntity(constant.BACKEND_URL + PROJECT_PATH, Project[].class);
//        projects.addAll(Arrays.asList(response.getBody()));
//
//        return projects;
//    }
//
//    public void saveProject(Project project) {
//        restTemplate.postForEntity(constant.BACKEND_URL + PROJECT_PATH, new HttpEntity<>(project), Object.class);
//    }

}
