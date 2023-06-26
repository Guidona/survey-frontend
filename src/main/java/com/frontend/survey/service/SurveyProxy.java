package com.frontend.survey.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.frontend.survey.data.Questionnaire;

public class SurveyProxy {

    private static String serverUrl = "http://34.78.194.119:8080";

    public static List<String> getQuestionnaires() {
        ResponseEntity<List<Questionnaire>> response = new RestTemplate().exchange(
                serverUrl.concat("/api/questionnaires"),
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Questionnaire>>() {
                });
        return response.getBody().stream().map(Questionnaire::getName).collect(Collectors.toList());
    }

    public static List<Questionnaire> getListQuestionnaires() {
        ResponseEntity<List<Questionnaire>> response = new RestTemplate().exchange(
                serverUrl.concat("/api/questionnaires"),
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Questionnaire>>() {
                });
        return response.getBody();
    }

    public static void exportFormulaires(Long formulaireId) {
        new RestTemplate().getForEntity(serverUrl.concat("/api/flat-export/formulaires?questionnaireId"), String.class);
    }

    public static String getDownloadLink(Long formulaireId) {
        return serverUrl + "/api/flat-export/formulaires?questionnaireId=" + formulaireId;
    }
}
