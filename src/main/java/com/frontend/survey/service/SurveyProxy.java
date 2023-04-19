package com.frontend.survey.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.frontend.survey.data.Questionnaire;

public class SurveyProxy {

    public static List<String> getQuestionnaires() {
        ResponseEntity<List<Questionnaire>> response = new RestTemplate().exchange(
                "http://35.195.235.84:8080/api/questionnaires",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Questionnaire>>() {
                });
        return response.getBody().stream().map(Questionnaire::getName).collect(Collectors.toList());
    }

    public static List<Questionnaire> getListQuestionnaires() {
        ResponseEntity<List<Questionnaire>> response = new RestTemplate().exchange(
                "http://35.195.235.84:8080/api/questionnaires",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Questionnaire>>() {
                });
        return response.getBody();
    }

    public static void exportFormulaires(Long formulaireId) {
        String url = "http://35.195.235.84:8080/api/flat-export/formulaires?questionnaireId";
        new RestTemplate().getForEntity(url, String.class);
    }

    public static String getDownloadLink(Long formulaireId) {
        return "http://35.195.235.84:8080/api/flat-export/formulaires?questionnaireId=" + formulaireId;
    }
}
