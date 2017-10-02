package ru.sbertech.datahub.ui.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sbertech.datahub.ui.config.Constant;

import ru.sbertech.datahub.webservice.model.metric.idle.IdleTestStandProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class IdleRestClient {
    @Autowired
    Constant constant;
    @Autowired
    RestTemplate restTemplate;
    private String IDLE_PATH = "api/metric/idles/getList";

    public List<IdleTestStandProblem> getIdles() {
        List<IdleTestStandProblem> Idles = new ArrayList<>();

        ResponseEntity<IdleTestStandProblem[]> response = restTemplate.getForEntity(constant.WEB_SERVICE_URL + IDLE_PATH, IdleTestStandProblem[].class, "releaseCode=");
        Idles.addAll(Arrays.asList(response.getBody()));

        return Idles;
    }

    public void saveIdle(IdleTestStandProblem Idle) {
        restTemplate.postForEntity(constant.BACKEND_URL + IDLE_PATH, new HttpEntity<>(Idle), Object.class);
    }
}
