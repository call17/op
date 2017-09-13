package ru.sbrf.metrica.vaadin.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sbrf.metrica.vaadin.config.Constant;
import ru.sbrf.metrica.vaadin.model.Idle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class IdleRestClient {
    @Autowired
    Constant constant;
    @Autowired
    RestTemplate restTemplate;
    private String IDLE_PATH = "/api/idles";

    public List<Idle> getIdles() {
        List<Idle> Idles = new ArrayList<>();

        ResponseEntity<Idle[]> response = restTemplate.getForEntity(constant.BACKEND_URL + IDLE_PATH, Idle[].class);
        Idles.addAll(Arrays.asList(response.getBody()));

        return Idles;
    }

    public void saveIdle(Idle Idle) {
        restTemplate.postForEntity(constant.BACKEND_URL + IDLE_PATH, new HttpEntity<>(Idle), Object.class);
    }
}
