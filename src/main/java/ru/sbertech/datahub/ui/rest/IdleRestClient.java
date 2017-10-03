package ru.sbertech.datahub.ui.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sbertech.datahub.ui.config.Constant;
import ru.sbertech.datahub.webservice.model.metric.idle.IdleTestStandProblem;
import ru.sbertech.datahub.webservice.model.metric.idle.IdleTestStandProblemChartData;
import ru.sbertech.datahub.webservice.type.IdleTestStandProblemChartTypeEnum;
import ru.sbertech.datahub.webservice.type.RestErrorMessage;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class IdleRestClient {

    @Autowired
    Constant constant = new Constant();

    @Autowired
    RestTemplate restTemplate = new RestTemplate();
    private String IDLE_PATH = "api/metric/idles";

    public List<IdleTestStandProblem> getIdles() {
        List<IdleTestStandProblem> Idles = new ArrayList<>();

        ResponseEntity<IdleTestStandProblem[]> response = restTemplate.getForEntity(constant.WEB_SERVICE_URL + IDLE_PATH + "/getList", IdleTestStandProblem[].class, "releaseCode=");
        Idles.addAll(Arrays.asList(response.getBody()));

        return Idles;
    }

    public RestErrorMessage saveIdle(IdleTestStandProblem Idle) {
        restTemplate.postForEntity(constant.WEB_SERVICE_URL + IDLE_PATH + "/setIdle", new HttpEntity<>(Idle), Object.class);

        return new RestErrorMessage(true, "success");
    }

    public RestErrorMessage delIdle(BigInteger rowId) {
        restTemplate.postForEntity(constant.WEB_SERVICE_URL + IDLE_PATH + "/delIdle", new HttpEntity<>(rowId), Object.class);

        return new RestErrorMessage(true, "success");
    }

    public List<IdleTestStandProblemChartData> getIdleChartData(String chartType, String releaseCode) {
        List<IdleTestStandProblemChartData> chartData = new ArrayList<>();

//        ResponseEntity<IdleTestStandProblemChartData[]> response = restTemplate.getForEntity(constant.WEB_SERVICE_URL + IDLE_PATH + "/getChartData", IdleTestStandProblemChartData[].class, (Object) ("chartType=" + chartType), (Object) ("releaseCode=" + releaseCode));
//        chartData.addAll(Arrays.asList(response.getBody()));
        if (chartType.equals(IdleTestStandProblemChartTypeEnum.WEEK.getChartTypeName())) {
            chartData.add(new IdleTestStandProblemChartData("Пример 1", 12));
            chartData.add(new IdleTestStandProblemChartData("Пример 2", 8));
            chartData.add(new IdleTestStandProblemChartData("Пример 3", 4));
            chartData.add(new IdleTestStandProblemChartData("Пример 4", 60));
            chartData.add(new IdleTestStandProblemChartData("Пример 5", 2));
            chartData.add(new IdleTestStandProblemChartData("Пример 6", 3));
            chartData.add(new IdleTestStandProblemChartData("Пример 7", 11));
        } else {
            if (chartType.equals(IdleTestStandProblemChartTypeEnum.MONTH.getChartTypeName())) {
                chartData.add(new IdleTestStandProblemChartData("Пример 1", 6));
                chartData.add(new IdleTestStandProblemChartData("Пример 2", 5));
                chartData.add(new IdleTestStandProblemChartData("Пример 3", 1));
                chartData.add(new IdleTestStandProblemChartData("Пример 4", 45));
                chartData.add(new IdleTestStandProblemChartData("Пример 5", 19));
                chartData.add(new IdleTestStandProblemChartData("Пример 6", 13));
                chartData.add(new IdleTestStandProblemChartData("Пример 7", 11));
            } else {
                chartData.add(new IdleTestStandProblemChartData("Пример 1", 3));
                chartData.add(new IdleTestStandProblemChartData("Пример 2", 7));
                chartData.add(new IdleTestStandProblemChartData("Пример 3", 17));
                chartData.add(new IdleTestStandProblemChartData("Пример 4", 12));
                chartData.add(new IdleTestStandProblemChartData("Пример 5", 47));
                chartData.add(new IdleTestStandProblemChartData("Пример 6", 3));
                chartData.add(new IdleTestStandProblemChartData("Пример 7", 11));
            }
        }

        return chartData;
    }
}
